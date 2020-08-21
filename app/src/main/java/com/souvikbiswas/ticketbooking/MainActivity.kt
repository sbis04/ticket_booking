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

    private lateinit var day1: TextView
    private lateinit var day2: TextView
    private lateinit var day3: TextView
    private lateinit var day4: TextView
    private lateinit var day5: TextView
    private lateinit var day6: TextView
    private lateinit var day7: TextView

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

        day1 = day_1
        day2 = day_2
        day3 = day_3
        day4 = day_4
        day5 = day_5
        day6 = day_6
        day7 = day_7

        var isCoverView = false
        var isDescriptionView = false

        val initialConstraint = ConstraintSet()
        initialConstraint.clone(root)

        val finalConstraint = ConstraintSet()
        finalConstraint.clone(this, R.layout.final_view)

        val descriptionConstraint = ConstraintSet()
        descriptionConstraint.clone(this, R.layout.description_view)

        val mapOfDays: Map<TextView, TextView> = mapOf(
            day1 to date_1,
            day2 to date_2,
            day3 to date_3,
            day4 to date_4,
            day5 to date_5,
            day6 to date_6,
            day7 to date_7
        )

        val days: List<TextView> = listOf(day1, day2, day3, day4, day5, day6, day7)

        for (day in days) {
            day.setOnClickListener { selectDate(it as TextView, descriptionConstraint) }
        }

        for (day in mapOfDays) {
            day.value.setOnClickListener { selectDate(day.key, descriptionConstraint) }
        }

        coverImage.setOnClickListener {
            if (!isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                finalConstraint.applyTo(root)

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
