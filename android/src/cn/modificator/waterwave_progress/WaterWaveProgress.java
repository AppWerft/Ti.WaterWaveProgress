package cn.modificator.waterwave_progress;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Path.Direction;
import android.graphics.Region.Op;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import org.appcelerator.kroll.common.Log;

/**
 * @author Administrator
 * 
 */
public class WaterWaveProgress extends View {

	private static final String LCAT = "WaterView";
	// 水的画笔 // 画圆环的画笔// 进度百分比的画笔
	private Paint mPaintWater = null, mRingPaint = null, mTextPaint = null;

	//Ring ring color // // background color from the current color waves progress // // // background color waves and waves between the progress bar // progress percentage of the font size
	// //进度百分比字体颜色
	private int mRingColor, mRingBgColor, mWaterColor, mWaterBgColor,
			mFontSize, mTextColor;
	// 进度 //浪峰个数
	float crestCount = 1.5f;

	int mProgress = 10, mMaxProgress = 100;

	// Canvas center
	private Point mCenterPoint;
	// Ring width
	private float mRingWidth, mRing2WaterWidth;
	// Whether to display the progress bar // whether to display the percentage of progress
	private boolean mShowRing = false, mShowNumerical = true;

	/** Factors generating waves effect */
	private long mWaveFactor = 0L;
	/** It is implemented wave animation */
	private boolean isWaving = true;
	/** amplitude */
	private float mAmplitude = 30.0F; // 20F
	/** Wave velocity */
	private float mWaveSpeed = 0.070F; // 0.020F
	/** Transparency of the water */
	private int mWaterAlpha = 255; // 255

	private MyHandler mHandler = null;

	private static class MyHandler extends Handler {
		private WeakReference<WaterWaveProgress> mWeakRef = null;
		private int refreshPeriod = 100;
		public MyHandler(WaterWaveProgress host) {
			mWeakRef = new WeakReference<WaterWaveProgress>(host);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (mWeakRef.get() != null) {
				mWeakRef.get().invalidate();
				sendEmptyMessageDelayed(0, refreshPeriod);
			}
		}
	}

	/* CONSTRUCTOR */
	public WaterWaveProgress(Context context) {
		super(context);
		mCenterPoint = new Point();
		
	}

	public void initView(Context context) {
		
			// 如果手机版本在4.0以上,则开启硬件加速
		if (VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
			setLayerType(View.LAYER_TYPE_HARDWARE, null);
			// setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		mRingPaint = new Paint();
		mRingPaint.setAntiAlias(true);
		mRingPaint.setColor(mRingColor); // 圆环颜色
		mRingPaint.setStyle(Paint.Style.STROKE);
		mRingPaint.setStrokeWidth(mRingWidth); // 圆环宽度

		mPaintWater = new Paint();
		mPaintWater.setStrokeWidth(1.0F);
		mPaintWater.setColor(mWaterColor);
		// mPaintWater.setColor(getResources().getColor(mWaterColor));
		//mPaintWater.setAlpha(mWaterAlpha);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(mTextColor);
		mTextPaint.setStyle(Paint.Style.FILL);
		mTextPaint.setTextSize(mFontSize);

		mHandler = new MyHandler(this);
	}

	public void animateWave() {
		if (!isWaving) {
			mWaveFactor = 0L;
			isWaving = true;
			mHandler.sendEmptyMessage(0);
		}
	}

	@SuppressLint({ "DrawAllocation", "NewApi" })
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d(LCAT, "========================≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠");
		// Get the entire View (container) width, height
		int width = getWidth();
		int height = getHeight();
		width = height = (width < height) ? width : height;
		mAmplitude = width / 20f;
		Log.d(LCAT, "width height amp " + width + "   " + height + "  "
				+ mAmplitude);
		mCenterPoint.x = width / 2;
		mCenterPoint.y = height / 2;
		{ // Reset the progress bar width and distance between the waves and the
			// progress bar,, as to why write this, I can not smoke a head
			mRingWidth = mRingWidth == 0 ? width / 20 : mRingWidth;
			mRing2WaterWidth = mRing2WaterWidth == 0 ? mRingWidth * 0.6f
					: mRing2WaterWidth;
			mRingPaint.setStrokeWidth(mRingWidth);
			mTextPaint.setTextSize(mFontSize == 0 ? width / 5 : mFontSize);
			if (VERSION.SDK_INT == VERSION_CODES.JELLY_BEAN) {
				setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			} else {
				setLayerType(View.LAYER_TYPE_HARDWARE, null);
			}
		}
		Log.d(LCAT, " mFontSize=" + mFontSize);
		Log.d(LCAT, "");

		RectF oval = new RectF();
		oval.left = mRingWidth / 2;
		oval.top = mRingWidth / 2;
		oval.right = width - mRingWidth / 2;
		oval.bottom = height - mRingWidth / 2;

		if (isInEditMode()) {
			mRingPaint.setColor(mRingBgColor);
			canvas.drawArc(oval, -90, 360, false, mRingPaint);
			mRingPaint.setColor(mRingColor);
			canvas.drawArc(oval, -90, 90, false, mRingPaint);
			canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x
					- mRingWidth - mRing2WaterWidth, mPaintWater);
			return;
		}

		// If no wave animation, or you do not specify the width and height of
		// the container, to draw a simple rectangle
		if ((width == 0) || (height == 0) || isInEditMode()) {
			canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, width / 2
					- mRing2WaterWidth - mRingWidth, mPaintWater);
			Log.d(LCAT, "no animation");
			return;
		}

		// From the water and the border
		float waterPadding = mShowRing ? mRingWidth + mRing2WaterWidth : 0;
		// Water highest point
		int waterHeightCount = mShowRing ? (int) (height - waterPadding * 2)
				: height;

		// Rebuild the wave shape
		mWaveFactor++;
		if (mWaveFactor >= Integer.MAX_VALUE) {
			mWaveFactor = 0L;
		}

		// Videos progress bar background
		mRingPaint.setColor(mRingBgColor);
		// canvas.drawArc(oval, -90, 360, false, mRingPaint);
		// //And following the same effect, but this is to draw an arc of 360
		// degrees, the following is drawing ring
		canvas.drawCircle(width / 2, width / 2, waterHeightCount / 2
				+ waterPadding - mRingWidth / 2, mRingPaint);
		mRingPaint.setColor(mRingColor);
		// 100 is the total schedule
		canvas.drawArc(oval, -90, (mProgress * 1f) / mMaxProgress * 360f,
				false, mRingPaint);

		// Calculate the height of the water
		float waterHeight = waterHeightCount
				* (1 - (mProgress * 1f) / mMaxProgress) + waterPadding;
		int staticHeight = (int) (waterHeight + mAmplitude);
		Path mPath = new Path();
		mPath.reset();
		if (mShowRing) {
			mPath.addCircle(width / 2, width / 2, waterHeightCount / 2,
					Direction.CCW);
		} else {
			mPath.addCircle(width / 2, width / 2, waterHeightCount / 2,
					Direction.CCW);
		}
		// canvas Add restrictions, so the next drawing in the park
		canvas.clipPath(mPath, Op.REPLACE);
		Paint bgPaint = new Paint();
		bgPaint.setColor(mWaterBgColor);
		// Draw background
		canvas.drawRect(waterPadding, waterPadding, waterHeightCount
				+ waterPadding, waterHeightCount + waterPadding, bgPaint);
		// Draw and still water
		canvas.drawRect(waterPadding, staticHeight, waterHeightCount
				+ waterPadding, waterHeightCount + waterPadding, mPaintWater);

		// x coordinate to be drawn wavy lines
		int xToBeDrawed = (int) waterPadding;
		int waveHeight = (int) (waterHeight - mAmplitude
				* Math.sin(Math.PI
						* (2.0F * (xToBeDrawed + (mWaveFactor * width)
								* mWaveSpeed)) / width));
		// Height wavy lines
		int newWaveHeight = waveHeight;
		while (true) {
			if (xToBeDrawed >= waterHeightCount + waterPadding) {
				break;
			}
			// Calculate the height of the wavy line based on the current value
			// of x
			newWaveHeight = (int) (waterHeight - mAmplitude
					* Math.sin(Math.PI
							* (crestCount * (xToBeDrawed + (mWaveFactor * waterHeightCount)
									* mWaveSpeed)) / waterHeightCount));

			// To draw up the top edge of the trapezoid
			canvas.drawLine(xToBeDrawed, waveHeight, xToBeDrawed + 1,
					newWaveHeight, mPaintWater);

			// Draw dynamic part of the column
			canvas.drawLine(xToBeDrawed, newWaveHeight, xToBeDrawed + 1,
					staticHeight, mPaintWater);
			xToBeDrawed++;
			waveHeight = newWaveHeight;
		}
		if (mShowNumerical) {
			String progressTxt = String.format("%.0f", (mProgress * 1f)
					/ mMaxProgress * 100f)
					+ "%";
			Log.d(LCAT, "progressTxt=" + progressTxt);
			float mTxtWidth = mTextPaint.measureText(progressTxt, 0,
					progressTxt.length());
			canvas.drawText(progressTxt, mCenterPoint.x - mTxtWidth / 2,
					mCenterPoint.x * 1.5f - mFontSize / 2, mTextPaint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = widthMeasureSpec;
		int height = heightMeasureSpec;
		width = height = (width < height) ? width : height;
		setMeasuredDimension(width, height);
	}

	public void setAmplitude(float amplitude) {
		mAmplitude = amplitude;
	}

	public void setWaterAlpha(float alpha) {
		mWaterAlpha = (int) (255.0F * alpha);
		//mPaintWater.setAlpha(mWaterAlpha);
	}

	public void setProgress(int progress) {
		progress = progress > 100 ? 100 : progress < 0 ? 0 : progress;
		mProgress = progress;
		invalidate();
	}

	public int getProgress() {
		return mProgress;
	}

	public void setWaveSpeed(float speed) {
		mWaveSpeed = speed;
	}

	public void setShowRing(boolean arg) {
		mShowRing = arg;
	}

	public void setShowNumerical(boolean arg) {
		mShowNumerical = arg;
	}

	public void setRingColor(int mRingColor) {
		this.mRingColor = mRingColor;
	}

	public void setRingBgColor(int mRingBgColor) {
		this.mRingBgColor = mRingBgColor;
	}

	public void setWaterColor(int mWaterColor) {
		this.mWaterColor = mWaterColor;
	}

	public void setWaterBgColor(int mWaterBgColor) {
		this.mWaterBgColor = mWaterBgColor;
	}

	public void setFontSize(int mFontSize) {
		this.mFontSize = mFontSize;
	}

	public void setTextColor(int mTextColor) {
		this.mTextColor = mTextColor;
	}

	public void setMaxProgress(int mMaxProgress) {
		this.mMaxProgress = mMaxProgress;
	}

	public void setCrestCount(float crestCount) {
		this.crestCount = crestCount;
	}

	public void setRing2WaterWidth(float arg) {
		this.mRing2WaterWidth = arg;
	}

	public void setRingWidth(float width) {
		this.mRingWidth = width;
	}

	public void setIsWaving(boolean arg) {
		this.isWaving = arg;
	}

	public void setWaveFactor(long arg) {
		this.mWaveFactor = arg;
	}

}