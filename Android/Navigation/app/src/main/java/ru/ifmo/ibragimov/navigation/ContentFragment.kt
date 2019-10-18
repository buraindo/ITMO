package ru.ifmo.ibragimov.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.content.view.*

class ContentFragment : Fragment() {

    private var level = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.level = arguments?.getInt(LEVEL) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content, container, false)
        with(view) {
            level.text = (0..this@ContentFragment.level).joinToString(SEPARATOR)
            button.setOnClickListener {
                requireFragmentManager().beginTransaction()
                    .replace(R.id.container, ContentFragment().apply {
                        arguments = Bundle().apply {
                            putInt(LEVEL, this@ContentFragment.level + 1)
                        }
                    }, it.id.toString())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return view
    }

    companion object {
        private const val LEVEL = "level"
        private const val SEPARATOR = "->"
    }

}