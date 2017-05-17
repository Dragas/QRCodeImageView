import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.ByteMatrix
import com.google.zxing.qrcode.encoder.Encoder
import lt.cpartner.apps.kyani.R

/**
 * Created by mgrid on 2017-01-16.
 */
class QRCodeImageView : ImageView
{
    private val paint: Paint = Paint()
    var matrix: ByteMatrix? = null
        set(matrix)
        {
            field = matrix
            invalidate()
            requestLayout()
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
    {
        val attribute = context.theme.obtainStyledAttributes(attrs, R.styleable.QRCodeImageView, 0, 0)
        val text = attribute.getString(R.styleable.QRCodeImageView_matrix)
        if (text != null)
            setMatrix(text)
        attribute.recycle()
    }

    override fun onDraw(canvas: Canvas)
    {
        if (this.matrix == null)
        {
            super.onDraw(canvas)
            return
        }
        val bitMatrix = this.matrix ?: return
        val widthPerDot = width / bitMatrix.width
        val heightPerDot = height / bitMatrix.height
        val offsetWidth = (width % bitMatrix.width) / 2
        val offsetHeight = (height % bitMatrix.height) / 2
        for (i in 0..bitMatrix.height - 1)
        {
            for (j in 0..bitMatrix.width - 1)
            {
                val bit = bitMatrix.get(i, j)
                if (bit == 1.toByte())
                    paint.color = Color.BLACK
                else
                    paint.color = Color.WHITE
                canvas.drawRect(i * widthPerDot.toFloat() + offsetWidth, j * heightPerDot.toFloat() + offsetHeight, (i + 1) * widthPerDot.toFloat() + offsetWidth, (j + 1) * heightPerDot.toFloat() + offsetHeight, paint)
            }
        }
    }

    fun setMatrix(text: String)
    {
        matrix = Encoder.encode(text, ErrorCorrectionLevel.H).matrix
    }
}
