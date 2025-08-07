package io.github.gyeyangcat.meal

import java.awt.*
import java.io.File
import java.util.Properties
import javax.imageio.ImageIO


fun Graphics.drawFont(color: Color, font: String, y: Int, width: Int, size: Float, leading: Int, txt: String) {
    this.color = color
    this.font = AddTextToImg.getFont(font).deriveFont(size)
    val metrics = getFontMetrics(this.font)
    txt.split("\n").forEachIndexed { ind, str ->
        val x = (width - metrics.stringWidth(str)) / 2

        drawString(str,
            x,y + ind * leading)
    }
}

object AddTextToImg {

    fun execute(file: File, lunch: String, date: String, out: File) {
        //read the image
        val image = ImageIO.read(file)
        //get the Graphics object
        val g = image.createGraphics()

        g.setRenderingHints(RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON))
        g.drawFont(Color(175, 212, 133), "assets/font/GmarketSansTTFBold.ttf", 217, image.width, 52.0f, 0, date)
        g.drawFont(Color(0, 0, 0), "assets/font/GmarketSansTTFBold.ttf", 468, image.width, 50.0f, 65, lunch)

        g.dispose()
        //write the image
        out.mkdirs()
        ImageIO.write(image, "png", out)
    }

    fun getFont(font: String): Font {
        val font: Font = Font.createFont(Font.TRUETYPE_FONT, File(font))
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(font)
        return font
    }

}