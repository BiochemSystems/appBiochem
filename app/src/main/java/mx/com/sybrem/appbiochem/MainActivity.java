package mx.com.sybrem.appbiochem;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    private Spinner lstViewFamilia;
    private Spinner lstViewSubtipo;
    private Spinner lstViewTipoVenta;
    private Spinner lstViewConducto;
    private AutoCompleteTextView textClientes;
    private AutoCompleteTextView autoCompleteProducto1;
    private AutoCompleteTextView autoCompleteProducto2;
    private AutoCompleteTextView autoCompleteProducto3;
    private AutoCompleteTextView autoCompleteProducto4;
    private AutoCompleteTextView autoCompleteProducto5;
    private AutoCompleteTextView autoCompleteProducto6;
    private AutoCompleteTextView autoCompleteProducto7;
    private AutoCompleteTextView autoCompleteProducto8;
    private AutoCompleteTextView autoCompleteProducto9;
    private AutoCompleteTextView autoCompleteProducto10;

    //ARRAY PARA LOS CLIENTES
    private String[] clientes = {"Atanacio Macias de Anda", "Fernando Lopez Partida", "Maria Guadalupe Luna", "Agricol el Vencedor"};

    //ARRAY PARA LOS PRODUCTOS
    private String[] productos = {"Shampoo Aloe Vera", "Garrametrin Forte", "Pulgaton Shampoo", "Control Rat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        ///PARTE PARA EL AUTOCOMPLETE DE LOS CLIENTES
        textClientes = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapterClientes = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes);
        textClientes.setAdapter(adapterClientes);

        ///PARTE PARA EL AUTOCOMPLETE DE LOS PRODUCTOS
        autoCompleteProducto1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto1);
        ArrayAdapter adapterProductos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto1.setAdapter(adapterProductos);

        autoCompleteProducto2 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto2);
        ArrayAdapter adapterProductos2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto2.setAdapter(adapterProductos2);

        autoCompleteProducto3 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto3);
        ArrayAdapter adapterProductos3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto3.setAdapter(adapterProductos3);

        autoCompleteProducto4 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto4);
        ArrayAdapter adapterProductos4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto4.setAdapter(adapterProductos4);

        autoCompleteProducto5 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto5);
        ArrayAdapter adapterProductos5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto5.setAdapter(adapterProductos5);

        autoCompleteProducto6 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto6);
        ArrayAdapter adapterProductos6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto6.setAdapter(adapterProductos6);

        autoCompleteProducto7 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto7);
        ArrayAdapter adapterProductos7 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto7.setAdapter(adapterProductos7);

        autoCompleteProducto8 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto8);
        ArrayAdapter adapterProductos8 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto8.setAdapter(adapterProductos8);

        autoCompleteProducto9 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto9);
        ArrayAdapter adapterProductos9 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto9.setAdapter(adapterProductos9);

        autoCompleteProducto10 = (AutoCompleteTextView)findViewById(R.id.autoCompleteProducto10);
        ArrayAdapter adapterProductos10 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos);
        autoCompleteProducto10.setAdapter(adapterProductos10);

        ///PARTE PARA LOS TABS DEL FORMULARIO
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Encabezado", //si se quita lo que esta en comillas aparecera un icono el que se selecciono
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Productos",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            public void onTabChanged(String tabId){
                Log.i("AndroidTabsDemo", "Pulsada pestaña: "+ tabId);
            }
        });

        ///PARA PONER LOS VALORES DEL LISTADO DE FAMILIA
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.valores_familia, android.R.layout.simple_spinner_item);
        lstViewFamilia = (Spinner)findViewById(R.id.lstViewFamilia);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstViewFamilia.setAdapter(adaptador);

        ///PARA PONER LOS VALORES DE LISTADO DE SUB TIPO
        ArrayAdapter<CharSequence> adaptador2 = ArrayAdapter.createFromResource(this, R.array.valores_sub_tipo_veterinaria, android.R.layout.simple_spinner_item);
        lstViewSubtipo = (Spinner)findViewById(R.id.lstViewSubtipo);
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstViewSubtipo.setAdapter(adaptador2);

        ///PARA PONER LOS VALORES DEL TIPO DE VENTA
        ArrayAdapter<CharSequence> adaptador3 = ArrayAdapter.createFromResource(this, R.array.valores_tipo_venta, android.R.layout.simple_spinner_item);
        lstViewTipoVenta = (Spinner)findViewById(R.id.lstViewTipoVenta);
        adaptador3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstViewTipoVenta.setAdapter(adaptador3);

        ///PARA PONER LOS VALORES DEL CONDUCTO
        ArrayAdapter<CharSequence> adaptador4= ArrayAdapter.createFromResource(this, R.array.valores_conductos, android.R.layout.simple_spinner_item);
        lstViewConducto = (Spinner)findViewById(R.id.lstViewConducto);
        adaptador4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstViewConducto.setAdapter(adaptador4);

    }


}
