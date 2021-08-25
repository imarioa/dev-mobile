package com.example.farmacias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.farmacias.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap
            Farmacias().forEach{
                mMap.addMarker(MarkerOptions()
                    .position
                (it.value)
                    .title(it.key))

            }
            mMap.moveCamera (CameraUpdateFactory.newLatLngZoom(LatLng(-3.780254228167808, -38.47562110547197), 10.0F))
            // Add a marker in Sydney and move the camera
            /*val sydney = LatLng(-34.0, 151.0)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))*/

        }

        fun Farmacias(): HashMap<String, LatLng>{
            val farmacias: HashMap<String, LatLng> = HashMap<String, LatLng>()

             farmacias.put("PagMenos 1" ,LatLng(-3.835215213927048, -38.52172237925478))
            farmacias.put("PagMenos 2" ,LatLng(-3.805273339717364, -38.55492812527693))
            farmacias.put("PagMenos 3" ,LatLng(-3.7117307274969806, -38.561967037319114))
            farmacias.put("PagMenos 4" ,LatLng(-3.7175549522841598, -38.51012530394945))
            farmacias.put("PagMenos 5" ,LatLng(-3.743934781961968, -38.5372478002157))


            farmacias.put(("Extra Farma 1"), LatLng(-3.7898519872037824, -38.567974655614))
            farmacias.put("Extra Farma 2", LatLng(-3.7473720242021114, -38.52952250900869))
            farmacias.put("Extra Farma 3", LatLng(-3.7494105496027026, -38.48712294679975))
            farmacias.put("Extra Farma 4", LatLng(-3.7449568955916304, -38.522141866029585))
            farmacias.put("Extra Farma 5", LatLng(-3.7857241101947507, -38.53862135743186))
            farmacias.put("Extra Farma 6", LatLng(-3.7319383925019363, -38.50875227926524))


            farmacias.put("Drogasil 1", LatLng(-3.7994382582951074, -38.571751471985245))
            farmacias.put("Drogasil 2", LatLng(-3.784365127568704, -38.47527778273442))
            farmacias.put("Drogasil 3", LatLng(-3.815538811573545, -38.497078776568664))
            farmacias.put("Drogasil 4", LatLng(-3.7244405320409704, -38.50668790089046))
            farmacias.put("Drogasil 5", LatLng(-3.7388015727105364, -38.47544944119129))

            return farmacias
        }
}