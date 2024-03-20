package com.tolgakurucay.mynotebooknew.presentation.custom

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.tolgakurucay.mynotebooknew.presentation.theme.imageSizeMedium





@Composable
fun CustomLoading(
    size: Dp = imageSizeMedium, // indicator size
    sweepAngle: Float = 90f, // angle (lenght) of indicator arc
    color: Color = MaterialTheme.colorScheme.primary, // color of indicator arc line
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth
) {
    /**
     * Animation
     * docs recomend use transition animation for infinite loops
     * https://developer.android.com/jetpack/compose/animation
     */
    val transition = rememberInfiniteTransition()


    val currentArcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        ), label = ""
    )

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    Canvas(
        Modifier
            .progressSemantics() // (optional) for Accessibility services
            .size(size) // canvas size
            .padding(strokeWidth / 2) //padding. otherwise, not the whole circle will fit in the canvas
    ) {
        // draw "background" (gray) circle with defined stroke.
        // without explicit center and radius it fit canvas bounds
        drawCircle(Color.LightGray, style = stroke)

        // draw arc with the same stroke
        drawArc(
            color,
            // arc start angle
            // -90 shifts the start position towards the y-axis
            startAngle = currentArcStartAngle.toFloat() - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }



}