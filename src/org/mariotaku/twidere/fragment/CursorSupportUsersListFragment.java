/*
 * 				Twidere - Twitter client for Android
 *
 *  Copyright (C) 2012-2013 Mariotaku Lee <mariotaku.lee@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.fragment;

import java.util.List;

import org.mariotaku.twidere.loader.BaseCursorSupportUsersLoader;
import org.mariotaku.twidere.model.ParcelableUser;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

public abstract class CursorSupportUsersListFragment extends BaseUsersListFragment {

	private long mNextCursor, mPrevCursor;

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mNextCursor = savedInstanceState.getLong(INTENT_KEY_NEXT_CURSOR, -1);
			mPrevCursor = savedInstanceState.getLong(INTENT_KEY_PREV_CURSOR, -1);
		} else {
			mNextCursor = -1;
			mPrevCursor = -1;
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mNextCursor = -1;
		mPrevCursor = -1;
	}

	@Override
	public void onLoaderReset(final Loader<List<ParcelableUser>> loader) {
		super.onLoaderReset(loader);
		mNextCursor = -1;
		mPrevCursor = -1;
	}

	@Override
	public void onLoadFinished(final Loader<List<ParcelableUser>> loader, final List<ParcelableUser> data) {
		super.onLoadFinished(loader, data);
		final BaseCursorSupportUsersLoader c_loader = (BaseCursorSupportUsersLoader) loader;
		mNextCursor = c_loader.getNextCursor();
		mPrevCursor = c_loader.getPrevCursor();
		setMode(mNextCursor > 0 ? Mode.PULL_FROM_END : Mode.DISABLED);
	}

	@Override
	public void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putLong(INTENT_KEY_NEXT_CURSOR, mNextCursor);
		outState.putLong(INTENT_KEY_PREV_CURSOR, mPrevCursor);
	}

	protected final long getNextCursor() {
		return mNextCursor;
	}

	protected final long getPrevCursor() {
		return mPrevCursor;
	}

	@Override
	protected abstract BaseCursorSupportUsersLoader newLoaderInstance(final Context context, final Bundle args);
}
