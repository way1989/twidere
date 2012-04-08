/*
 *              Copyright (C) 2011 The MusicMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mariotaku.twidere.activity;

import org.mariotaku.twidere.Constants;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;

public class LicenseActivity extends WebViewActivity implements Constants {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadUrl("file:///android_asset/gpl-3.0-standalone.html");
		setWebViewClient(new LicenseWebViewClient());

	}
	
	private class LicenseWebViewClient extends DefaultWebViewClient {
		
		@Override
		public void onPageFinished(WebView view, String url) {
			setTitle(view.getTitle());
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
		}
	}
}