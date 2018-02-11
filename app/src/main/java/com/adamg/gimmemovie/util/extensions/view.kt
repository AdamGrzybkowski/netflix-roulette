package com.adamg.gimmemovie.util.extensions

import android.support.constraint.ConstraintLayout
import android.support.constraint.Group
import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

/**
 * showing constraint layout group after beeing invisible / dirty hack
 */
fun Group.showGroup() {
    this.visibility = ConstraintLayout.GONE
    this.visibility = ConstraintLayout.VISIBLE
}

/**
 * making constraint layout group invisible / dirty hack
 */
fun Group.hideGroup() {
    this.visibility = ConstraintLayout.GONE
    this.visibility = ConstraintLayout.INVISIBLE
}