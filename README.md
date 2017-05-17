# QRCodeImageView
A snippet for Android to render QRCodes somewhat efficiently.

## Why?

Because ZXing renders QR codes that already accounts for particular dimensions you provide and scales it for you. This usually results in matrices that are atleast 800x800 and have to be reassigned as well as return type being `BitMatrix` instead of `ByteMatrix`, which is safer for consumption. Sure if you need to reassign it only once, you could, but such solution isn't safe for pagers and/or lists they usually render 2+ elements at once. 

This solution skips the scaling step, and returns the smalled possible matrix so you could scale it yourself (or have it done for you while drawing by QRCodeImageView), which is much more efficient and can be used in lists. Sadly, QR codes depend on silent zones, so you should have margins around this object in your layout.

## Requirements

Usually you should just have [ZXing](https://github.com/zxing/zxing) dependency for this to work.
