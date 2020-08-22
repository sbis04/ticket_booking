package com.souvikbiswas.ticketbooking

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
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
    private lateinit var descriptionButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        descriptionButton = description_button

        var isCoverView = false
        var isDescriptionView = false

        val initialConstraint = ConstraintSet()
        initialConstraint.clone(root)

        val coverConstraint = ConstraintSet()
        coverConstraint.clone(this, R.layout.cover_view)

        val descriptionConstraint = ConstraintSet()
        descriptionConstraint.clone(this, R.layout.description_view)

        val mapOfDays: Map<TextView, TextView> = mapOf(
            day_1 to date_1,
            day_2 to date_2,
            day_3 to date_3,
            day_4 to date_4,
            day_5 to date_5,
            day_6 to date_6,
            day_7 to date_7
        )

        val days: List<TextView> = listOf(day_1, day_2, day_3, day_4, day_5, day_6, day_7)

        for (day in days) {
            day.setOnClickListener { selectDate(it as TextView, descriptionConstraint) }
        }

        for (day in mapOfDays) {
            day.value.setOnClickListener { selectDate(day.key, descriptionConstraint) }
        }

        coverImage.setOnClickListener {
            if (!isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                coverConstraint.applyTo(root)

                val anim = ValueAnimator()
                anim.setIntValues(Color.BLACK, Color.WHITE)
                anim.setEvaluator(ArgbEvaluator())
                anim.addUpdateListener {
                    menuButton.setColorFilter(it.animatedValue as Int)
                    movieStatus.setTextColor(it.animatedValue as Int)
                    movieTitle.setTextColor(it.animatedValue as Int)
                    movieDescription.setTextColor(it.animatedValue as Int)
                    movieRating.setTextColor(it.animatedValue as Int)
                    descriptionButton.setColorFilter(it.animatedValue as Int)
                }

                anim.duration = 300
                anim.start()
                isCoverView = true
                isDescriptionView = false
            }

        }

        menuButton.setOnClickListener {
            if (isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                initialConstraint.applyTo(root)

                val anim = ValueAnimator()
                anim.setIntValues(Color.WHITE, Color.BLACK)
                anim.setEvaluator(ArgbEvaluator())
                anim.addUpdateListener {
                    menuButton.setColorFilter(it.animatedValue as Int)
                    movieStatus.setTextColor(it.animatedValue as Int)
                    movieTitle.setTextColor(it.animatedValue as Int)
                    movieDescription.setTextColor(it.animatedValue as Int)
                    movieRating.setTextColor(it.animatedValue as Int)
                    descriptionButton.setColorFilter(it.animatedValue as Int)
                }

                anim.duration = 300
                anim.start()
                isCoverView = false
                isDescriptionView = false
            } else if (isDescriptionView) {
                TransitionManager.beginDelayedTransition(root)
                initialConstraint.applyTo(root)

                isCoverView = false
                isDescriptionView = false
            }

        }

        descriptionButton.setOnClickListener {
            if (!isDescriptionView) {
                TransitionManager.beginDelayedTransition(root)
                descriptionConstraint.applyTo(root)

                if (isCoverView) {
                    val anim = ValueAnimator()
                    anim.setIntValues(Color.WHITE, Color.BLACK)
                    anim.setEvaluator(ArgbEvaluator())
                    anim.addUpdateListener {
                        menuButton.setColorFilter(it.animatedValue as Int)
                        movieStatus.setTextColor(it.animatedValue as Int)
                        movieTitle.setTextColor(it.animatedValue as Int)
                        movieDescription.setTextColor(it.animatedValue as Int)
                        movieRating.setTextColor(it.animatedValue as Int)
                        descriptionButton.setColorFilter(it.animatedValue as Int)
                    }

                    anim.duration = 300
                    anim.start()
                    isCoverView = false
                }


                isDescriptionView = true
            }
        }
    }

    private fun selectDate(day: TextView, destinationConstraint: ConstraintSet) {
        destinationConstraint.connect(
            date_selector.id,
            ConstraintSet.START,
            day.id,
            ConstraintSet.START
        )
        destinationConstraint.connect(
            date_selector.id,
            ConstraintSet.END,
            day.id,
            ConstraintSet.END
        )
        TransitionManager.beginDelayedTransition(root)
        destinationConstraint.applyTo(root)
    }
}
