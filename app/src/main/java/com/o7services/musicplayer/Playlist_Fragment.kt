package com.o7services.musicplayer

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.musicplayer.databinding.FragmentPlaylistBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Playlist_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Playlist_Fragment : Fragment(), MusicClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentPlaylistBinding
    lateinit var musicViewModel: MusicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlaylistBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = RecyclerAdapter(this)
        binding.recycler.layoutManager = LinearLayoutManager(mainActivity)
        binding.recycler.adapter = recyclerAdapter

        musicViewModel = ViewModelProvider(mainActivity)[MusicViewModel::class.java]


        musicViewModel.musicContentList.observe(mainActivity){
            recyclerAdapter.updateList(it)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Playlist_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Playlist_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun OnSongPlayClick(musicContent: MusicContent) {
        System.out.print("musicContent.storageLocation ${musicContent.storageLocation}")
        Log.e("TAG", "musicContent.storageLocation ${musicContent.storageLocation}")
        if(mainActivity.mediaPlayer.isPlaying){
            mainActivity.mediaPlayer.stop()
        }
        mainActivity.mediaPlayer = MediaPlayer.create(mainActivity, Uri.parse(musicContent.storageLocation))
        mainActivity.mediaPlayer.start()
    }
}