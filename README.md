# PDF_TO_TXT
use java ,code by others
要是没有什么特殊要求（图片解析）建议使用java的代码，使用OCR需要配置tesseract ocr环境

or use OCR（将pdf转换成图片再解析）效果不太好
## 1.download tesseract on windows
https://digi.bib.uni-mannheim.de/tesseract/
## 2.add Tesseract-OCR dir to path
## 3.add TESSDATA_PREFIX to path for use of different languages
  Tesseract-OCR\tessdata
## 4.download pytesseract for usage in python
  pip install pytesseract
在python中使用pytesseract会报编码错误，所以使用命令行代码
## 5.download poppler Windows
  https://poppler.freedesktop.org/
  https://blog.alivate.com.au/poppler-windows/
  其实里面也有自带的pdftotext，但是效果不好就随意啦，这里用到里面的 pdf2image
 
# 具体思路
### 1、使用pdf2image将pdf转换成图片
### 2、将图片转换成text

  
