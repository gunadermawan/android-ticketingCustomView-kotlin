package com.gunder.ticketingcustomview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.gunder.ticketingcustomview.R

class SeatsView : View {
    private val seat: Seat? = null
    private val backgroundPaint = Paint()
    private val armresPaint = Paint()
    private val bottomSeatPaint = Paint()
    private val mBounds = Rect()
    private val numberSeatPaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    private val titlePaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    private val seats: ArrayList<Seat> = arrayListOf(
        Seat(id = 1, name = "A1", isBooked = false),
        Seat(id = 2, name = "A2", isBooked = false),
        Seat(id = 3, name = "B1", isBooked = false),
        Seat(id = 4, name = "B2", isBooked = false),
        Seat(id = 5, name = "C1", isBooked = false),
        Seat(id = 6, name = "C2", isBooked = false),
        Seat(id = 7, name = "D1", isBooked = false),
        Seat(id = 8, name = "D2", isBooked = false),
    )

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (seat in seats) {
            drawSeat(canvas, seat)
        }
    }

    private fun drawSeat(canvas: Canvas?, seat: Seat) {
        if (seat.isBooked) {
            backgroundPaint.color = ResourcesCompat.getColor(resources, R.color.grey_200, null)
            armresPaint.color = ResourcesCompat.getColor(resources, R.color.grey_200, null)
            bottomSeatPaint.color = ResourcesCompat.getColor(resources, R.color.grey_200, null)
            numberSeatPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)

        } else {
            backgroundPaint.color = ResourcesCompat.getColor(resources, R.color.blue_500, null)
            armresPaint.color = ResourcesCompat.getColor(resources, R.color.blue_700, null)
            bottomSeatPaint.color = ResourcesCompat.getColor(resources, R.color.blue_200, null)
            numberSeatPaint.color = ResourcesCompat.getColor(resources, R.color.blue_200, null)
        }
        canvas?.save()
        canvas?.translate(seat.x as Float, seat.y as Float)

        // background
        val backgroundPath = Path()
        backgroundPath.addRect(0F, 0F, 200F, 200F, Path.Direction.CCW)
        backgroundPath.addCircle(100F, 50F, 75F, Path.Direction.CCW)
        canvas?.drawPath(backgroundPath, backgroundPaint)

        // armrest
        val armrestPath = Path()
        armrestPath.addRect(0F, 0F, 50F, 200F, Path.Direction.CCW)
        canvas?.drawPath(armrestPath, armresPaint)
        canvas?.translate(150f, 0f)
        armrestPath.addRect(0F, 0F, 50F, 200F, Path.Direction.CCW)
        canvas?.drawPath(armrestPath, armresPaint)

        // bottom seat
        canvas?.translate(-150F, 175F)
        val bottomSeatPath = Path()
        bottomSeatPath.addRect(0F, 0F, 200F, 25F, Path.Direction.CCW)
        canvas?.drawPath(bottomSeatPath, bottomSeatPaint)

        // seat number
        canvas?.translate(0F, -175F)
        numberSeatPaint.apply {
            textSize = 50F
            numberSeatPaint.getTextBounds(seat.name, 0, seat.name.length, mBounds)
        }
        canvas?.drawText(seat.name, 100F - mBounds.centerX(), 100F, numberSeatPaint)
        // reset canvas
        canvas?.restore()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)

        val halfOfHeight = height / 2
        val halfOfWidth = width / 2
        var value = -600f
        for (i in 0..7) {
            if (i.mod(2) == 0) {
                seats[i].apply {
                    x = halfOfWidth - 300f
                    y = halfOfHeight + value
                }
            } else {
                seats[i].apply {
                    x = halfOfWidth + 100f
                    y = halfOfHeight + value
                }
                value += 300f
            }
        }
    }
}