/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2016 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This is generated, do not edit by hand. **/

#include <jni.h>

#include "Proxy.h"

namespace ti {
namespace waterwaveprogress {
	namespace waterwaveprogress {

class ViewProxy : public titanium::Proxy
{
public:
	explicit ViewProxy();

	static void bindProxy(v8::Local<v8::Object>, v8::Local<v8::Context>);
	static v8::Local<v8::FunctionTemplate> getProxyTemplate(v8::Isolate*);
	static void dispose(v8::Isolate*);

	static jclass javaClass;

private:
	static v8::Persistent<v8::FunctionTemplate> proxyTemplate;

	// Methods -----------------------------------------------------------
	static void hideRing(const v8::FunctionCallbackInfo<v8::Value>&);
	static void showRing(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setAmplitude(const v8::FunctionCallbackInfo<v8::Value>&);
	static void hideNumerical(const v8::FunctionCallbackInfo<v8::Value>&);
	static void showNumerical(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setCrestCount(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setRingWidth(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setProgress(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setWaterAlpha(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setWaveSpeed(const v8::FunctionCallbackInfo<v8::Value>&);

	// Dynamic property accessors ----------------------------------------
	static void setter_progress(v8::Local<v8::Name> name, v8::Local<v8::Value> value, const v8::PropertyCallbackInfo<void>& info);

};

	} // namespace waterwaveprogress
} // waterwaveprogress
} // ti
