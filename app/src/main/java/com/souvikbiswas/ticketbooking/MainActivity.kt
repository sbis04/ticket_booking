package com.souvikbiswas.ticketbooking

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var coverImage: View
    private lateinit var menuButton: ImageButton
    private lateinit var movieStatus: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView
    private lateinit var movieRating: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        addConstraintSetAnimation()
    }

    private fun addConstraintSetAnimation() {
        coverImage = cover
        menuButton = menu_button
        movieStatus = status
        movieTitle = movie_title
        movieDescription = desc
        movieRating = rating

        var isCoverView = false

        val initialConstraint = ConstraintSet()
        initialConstraint.clone(root)

        val finalConstraint = ConstraintSet()
        finalConstraint.clone(this, R.layout.final_view)

        val descriptionConstraint = ConstraintSet()
        descriptionConstraint.clone(this, R.layout.description_view)

        coverImage.setOnClickListener {
            if (!isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                descriptionConstraint.applyTo(root)

//            movieStatus.setTextColor(Color.rgb(200,0,0))

//                val anim = ValueAnimator()
//                anim.setIntValues(Color.BLACK, Color.WHITE)
//                anim.setEvaluator(ArgbEvaluator())
//                anim.addUpdateListener {
//                    menuButton.setColorFilter(it.animatedValue as Int)
//                    movieStatus.setTextColor(it.animatedValue as Int)
//                    movieTitle.setTextColor(it.animatedValue as Int)
//                    movieDescription.setTextColor(it.animatedValue as Int)
//                    movieRating.setTextColor(it.animatedValue as Int)
//                }
//
//                anim.duration = 300
//                anim.start()
                isCoverView = true
            }

        }

        menuButton.setOnClickListener {
            if (isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                initialConstraint.applyTo(root)

//                val anim = ValueAnimator()
//                anim.setIntValues(Color.WHITE, Color.BLACK)
//                anim.setEvaluator(ArgbEvaluator())
//                anim.addUpdateListener {
//                    menuButton.setColorFilter(it.animatedValue as Int)
//                    movieStatus.setTextColor(it.animatedValue as Int)
//                    movieTitle.setTextColor(it.animatedValue as Int)
//                    movieDescription.setTextColor(it.animatedValue as Int)
//                    movieRating.setTextColor(it.animatedValue as Int)
//                }
//
//                anim.duration = 300
//                anim.start()
                isCoverView = false
            }

        }
    }
}