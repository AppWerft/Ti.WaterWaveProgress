/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2013 by Appcelerator, Inc. All Rights Reserved.
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
	explicit ViewProxy(jobject javaObject);

	static void bindProxy(v8::Handle<v8::Object> exports);
	static v8::Handle<v8::FunctionTemplate> getProxyTemplate();
	static void dispose();

	static v8::Persistent<v8::FunctionTemplate> proxyTemplate;
	static jclass javaClass;

private:
	// Methods -----------------------------------------------------------
	static v8::Handle<v8::Value> hideRing(const v8::Arguments&);
	static v8::Handle<v8::Value> showRing(const v8::Arguments&);
	static v8::Handle<v8::Value> setAmplitude(const v8::Arguments&);
	static v8::Handle<v8::Value> hideNumerical(const v8::Arguments&);
	static v8::Handle<v8::Value> showNumerical(const v8::Arguments&);
	static v8::Handle<v8::Value> setCrestCount(const v8::Arguments&);
	static v8::Handle<v8::Value> setRingWidth(const v8::Arguments&);
	static v8::Handle<v8::Value> setProgress(const v8::Arguments&);
	static v8::Handle<v8::Value> setWaterAlpha(const v8::Arguments&);
	static v8::Handle<v8::Value> setWaveSpeed(const v8::Arguments&);

	// Dynamic property accessors ----------------------------------------
	static void setter_progress(v8::Local<v8::String> property, v8::Local<v8::Value> value, const v8::AccessorInfo& info);

};

			} // namespace waterwaveprogress
		} // waterwaveprogress
		} // ti
