package org.abuhuraira.app.scenes.listVideos

import android.support.v4.app.Fragment
import android.content.Intent
import org.abuhuraira.app.common.VIDEO_ID
import org.abuhuraira.app.scenes.showVideo.ShowVideoActivity
import org.abuhuraira.app.scenes.listVideos.common.ListVideosRoutable
import java.lang.ref.WeakReference

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class ListVideosRouter(override var fragment: WeakReference<Fragment?>) : ListVideosRoutable {

    override fun showVideo(id: String) {
        // Replace list videos fragment with animation
        /*val showVideoFragment = ShowVideoFragment()
        showVideoFragment.id = id

        fragment.get()?.fragmentManager
                ?.beginTransaction()
                //?.setCustomAnimations(R.animator.right_enter, R.animator.left_exit,
                 //       R.animator.left_enter, R.animator.right_exit)
                ?.replace(R.id.base_fragment, showVideoFragment,
                        ShowVideoFragment::class.java.simpleName)
                ?.addToBackStack(ShowVideoFragment::class.java.simpleName)
                ?.commit()*/

        val intent  = Intent(fragment.get()?.activity, ShowVideoActivity::class.java)
        intent.putExtra(VIDEO_ID, id)
        fragment.get()?.startActivity(intent)
    }
}