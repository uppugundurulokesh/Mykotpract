package com.example.mykotpract

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Second : AppCompatActivity() {


    lateinit var recyclerview: RecyclerView

    var connectivity : ConnectivityManager?=null
    var info : NetworkInfo?=null
    val con: Context=this

    val strCategorylist : MutableList<String> = mutableListOf()
    val strThumblist : MutableList<String> = mutableListOf()
    val strDesc : MutableList<String> = mutableListOf()

    var url:String ="https://www.themealdb.com/api/json/v1/1/categories.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setTitle("Meals List")
        recyclerview=findViewById(R.id.recyclerView)

        recyclerview.layoutManager = LinearLayoutManager(this)

        if(isconnected()){
           // Toast.makeText(this,"connected",Toast.LENGTH_LONG).show()
            callCoroutines()
        }
        else{

            Toast.makeText(this,"No Internet connection...!",Toast.LENGTH_LONG).show()

        }

    }



    private fun isconnected() : Boolean{
        connectivity = con.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(connectivity != null){
            info=connectivity!!.activeNetworkInfo

            if(info!=null){
                if(info!!.state==NetworkInfo.State.CONNECTED){
                    return  true
                }
            }
        }
        return false

    }

    private fun callCoroutines() {
        CoroutineScope(Dispatchers.IO).launch {
            getrecepiData()
        }

    }



    suspend fun getrecepiData() {

        val url = URL(url)
        val httpsURLConnection : HttpsURLConnection = url.openConnection() as HttpsURLConnection
        val inputStream : InputStream = httpsURLConnection.inputStream
        val text = inputStream.bufferedReader().use(BufferedReader::readText)
        withContext(Dispatchers.Main){

            val root=JSONObject(text);
            val jarry=root.getJSONArray("categories")
            for( i in 0..jarry.length()-1){

                val po=jarry.getJSONObject(i);

                val strname=po.getString("strCategory")
                val strthumb=po.getString("strCategoryThumb")
                val strdesc=po.getString("strCategoryDescription")

                strCategorylist.add(strname)
                strThumblist.add(strthumb)
                strDesc.add(strdesc)

            }

            val adapterclass = RecepiAdapter(this@Second,strCategorylist,strThumblist,strDesc);
            recyclerview.adapter = adapterclass


        }

    }
}

class RecepiAdapter(val context: Second, strCategorylist: MutableList<String>, strThumblist: MutableList<String>, strDesc: MutableList<String>) : RecyclerView.Adapter<RecepiAdapter.ViewHolder>() {



    val strCategorylist:MutableList<String> = strCategorylist;
    val strThumblist:MutableList<String> = strThumblist;
    val strdesclist: MutableList<String> = strDesc;
    val c: Context=context




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecepiAdapter.ViewHolder {
        val v : View = LayoutInflater.from(context).inflate(R.layout.row,parent,false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return strCategorylist.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nametext.text = strCategorylist.get(position)
        Picasso.get().load(strThumblist.get(position)).placeholder(R.drawable.ic_cloud_download).into(holder.flag)

      
        holder.itemView.setOnClickListener {
           //Toast.makeText(context,strCategorylist.get(position),Toast.LENGTH_LONG).show();

            val intent = Intent(context,DetailsPage::class.java)
            intent.putExtra("itemname",strCategorylist.get(position))
            intent.putExtra("itemimg",strThumblist.get(position))
            intent.putExtra("itemdes",strdesclist.get(position))
            context.startActivity(intent)
        }
       


    }



    class ViewHolder(item : View) : RecyclerView.ViewHolder(item)
    {


        val nametext : TextView = itemView.findViewById(R.id.tvtext1);

        val flag : ImageView = itemView.findViewById(R.id.img1)



    }
}







