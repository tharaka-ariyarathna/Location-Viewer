package com.tharaka.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tharaka.myapplication.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var spinner: Spinner ;
    var lattitude = -34.0 ;
    var longitude = 151.0 ;
    var sydney = City("Sydney", -34.0, 151.0) ;
    var colombo = City("Colombo",79.861244,6.927079) ;
    val tokyo = City("Tokyo", 139.839478,35.652832) ;

    val cityCoordinates = arrayOf(sydney,colombo,tokyo) ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = findViewById(R.id.spinner) ;

        val cities = arrayOf("Sydney", "Colombo", "Tokyo") ;

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities) ;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.submit.text = cities.get(p2) ;
                showMap(cities.get(p2)) ;
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.submit.text = "Select Something" ;
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(lattitude, longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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

    fun showMap(cityName: String){
        for(x in cityCoordinates){
            if(x.name == cityName){
                lattitude = x.lattitude ;
                longitude = x.longitude ;
            }
        }

        /*lattitude = sydney.longitude ;
        longitude = sydney.lattitude ;*/
        /*binding.longitude.text.toString().toDouble()*/
        /*binding.lattitude.text.toString().toDouble()*/

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
}

/* <EditText
        android:id="@+id/lattitude"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="textPersonName"
        android:text="-34.00" />

    <EditText
        android:id="@+id/longitude"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="textPersonName"
        android:text="151.00"
        android:layout_below="@id/lattitude"/>

    <Button
        android:id="@+id/submit"
        android:layout_width="195dp"
        android:layout_height="wrap_content"
        android:layout_below="@id/longitude"
        android:layout_marginTop="17dp"
        android:text="Show" />*/