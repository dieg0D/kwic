package kwic.Export

import kwic.IndexManager
import kwic.Shift.SetWordShift
import kwic.Storage.SetStorageManager
import java.io.FileOutputStream
import java.io.IOException
import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter



class PDFExportManager : ExportManager{
    override fun print(){
        val WordShift = SetWordShift.setWordShift()
        val file = ("out.pdf")
        val doc : Document = Document()
        val ala = PdfWriter.getInstance(doc,FileOutputStream(file))
        doc.open()
        val dsm = SetStorageManager.setStorage()
        dsm.init()

        val im  = IndexManager()

        for(lineNumber in 0 until dsm.length()) {
            val line = dsm.line(lineNumber)
            val words = line.split(' ')
            for(pos in 0 until words.size) {
                im.map(words[pos], line, pos)
            }
        }
        lateinit var x : String
        for(w in im.sortedWords()){
            im.occurrencesOfWord(w)!!.forEach ({
                when(it){
                    it -> x = (WordShift.shift(it.first.split(" ").toList(),it.second ).joinToString(separator = " " ))
                }
                if(x.isNotEmpty()) {
                    doc.add(Paragraph(x))
                }
            })
        }
        doc.close()
    }

    override fun name() = "PDF"
}