package com.udacity.ui.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.udacity.R
import com.udacity.ui.ButtonState
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0
    private var progressStatus = 0f
    private var progressWheel = 0f

    private val valueAnimator by lazy {
        ValueAnimator.ofFloat(0f, widthSize.toFloat())
    }

    //attrs
    private var itemStyle = ItemStyle()

    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = resources.getDimension(R.dimen.default_text_size)
        typeface = Typeface.create("", Typeface.BOLD)
    }

    var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { _, _, new ->
        when (new) {
            ButtonState.Clicked -> processButtonClicked()
            ButtonState.Loading -> processButtonLoading()
            ButtonState.Completed -> processButtonCompleted()
        }
    }

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)

        itemStyle.loadingColor = typedArray.getColor(R.styleable.LoadingButton_loadingColor,
            itemStyle.loadingColor)

        itemStyle.btnTextColor = typedArray.getColor(R.styleable.LoadingButton_btnTextColor,
            itemStyle.btnTextColor)

        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            itemStyle.loadingColor =
                getColor(R.styleable.LoadingButton_loadingColor, itemStyle.loadingColor)
            itemStyle.btnTextColor =
                getColor(R.styleable.LoadingButton_btnTextColor, itemStyle.btnTextColor)
            itemStyle.btnCircleColor =
                getColor(R.styleable.LoadingButton_circleColor, itemStyle.btnCircleColor)
            itemStyle.btnText =
                getString(R.styleable.LoadingButton_btnLabel) ?: itemStyle.btnText

        }

        setContentDescription(resources.getString(R.string.button_default))

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            drawButtonProgress(it)
            drawButtonText(it)
            drawWheelProgress(it)
        }
    }

    private fun drawButtonProgress(canvas: Canvas) {
        paint.color = itemStyle.loadingColor
        canvas.drawRect(0f, 0f, progressStatus, heightSize.toFloat(), paint)
    }

    private fun drawButtonText(canvas: Canvas) {
        paint.color = itemStyle.btnTextColor
        pointPosition.set((width.div(2)).toFloat(),
            (height.div(2) - (paint.descent() + paint.ascent()).div(2)))

        canvas.drawText(itemStyle.btnText, pointPosition.x, pointPosition.y, paint)
    }

    private fun drawWheelProgress(canvas: Canvas) {
        canvas.translate(widthSize / 2 + paint.measureText(itemStyle.btnText) / 2 + (paint.textSize / 2),
            heightSize / 2 - paint.textSize / 2)
        paint.color = itemStyle.btnCircleColor
        canvas.drawArc(RectF(0f, 0f, paint.textSize, paint.textSize),
            0F,
            progressWheel * 0.365f,
            true,
            paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

    private fun setContentDescription(text: String) {
        contentDescription = text
    }

    //Button State Options

    private fun processButtonClicked() {
        itemStyle.btnText = resources.getString(R.string.button_clicked)
        setContentDescription(resources.getString(R.string.button_clicked))
        invalidate()
    }

    private fun processButtonLoading() {
        itemStyle.btnText = resources.getString(R.string.button_loading)
        setContentDescription(resources.getString(R.string.button_loading))
        isClickable = false

        valueAnimator.apply {
            addUpdateListener { valueAnimator ->
                progressStatus = valueAnimator.animatedValue as Float
                progressWheel = (widthSize.toFloat() / 365f) + progressStatus
                invalidate()
            }
            duration = itemStyle.btnLoadingDuration
            start()
        }
    }

    private fun processButtonCompleted() {
        isClickable = true
        itemStyle.btnText = resources.getString(R.string.button_default)
        setContentDescription(resources.getString(R.string.button_default))
        progressWheel = 0f
        progressStatus = 0f
        valueAnimator.cancel()
        invalidate()
    }
}
