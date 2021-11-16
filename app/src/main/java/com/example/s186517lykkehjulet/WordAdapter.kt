import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.s186517lykkehjulet.R


class WordAdapter(private val context: Context, private val letterId: String) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {


    private var alphabet: List<String>

    init {
        alphabet = context.resources.getStringArray(R.array.alphabet).toList()
    }

    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val wordButton = view.findViewById<Button>(R.id.wordButton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val curWord = alphabet[position]
//        holder.wordButton.text = curWord

    }

    override fun getItemCount() = alphabet.size + 11


}