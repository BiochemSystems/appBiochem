package mx.com.sybrem.appbiochem;

// Imports de los manejadores de la base de datos, contents y el cursor (Content y Cursor se usaran después)
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;


/*************************************************************************************
 * Created by mrendonr on 23/08/16.                                                  *
 * Clase usada para la ejecución de los querys con la base de datos local            *
 * 26/Agosto/2016: Se agrega el metodo getClientes().                                *
 *************************************************************************************/
public class MyDBHandler  extends SQLiteOpenHelper
{
    // final: Definición de constantes

    //Definición de la base de datos
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bladeMovil.db";

    // Definición de los nombres de las tablas de la base de datos
    public static final String TABLE_VN_CAT_CLIENTES = "vn_cat_clientes";
    public static final String TABLE_IN_CAT_PRODUCTOS = "in_cat_productos";
    public static final String TABLE_VN_CAT_CONDUCTOS= "vn_cat_conductos";
    public static final String TABLE_VN_PEDIDOS_ENCABEZADO = "vn_pedidos_encabezado";
    public static final String TABLE_VN_PEDIDOS_PARTIDAS = "vn_pedidos_partidas";
    public static final String TABLE_GL_ACCESOS = "gl_accesos";
    public static final String TABLE_GL_SYNC = "gl_sync";


    // Definición de los nombres de columnas de cada tabla

    // vn_cat_clientes:
    public static final String COL_VNCATCLIENTES_ID = "_id";
    public static final String COL_VNCATCLIENTES_CVEUSUARIO = "cve_usuario";
    public static final String COL_VNCATCLIENTES_NOMBRE = "nombre";
    public static final String COL_VNCATCLIENTES_RFC = "rfc";
    public static final String COL_VNCATCLIENTES_TIPOCONTRIBUYENTE = "tipo_contribuyente";
    public static final String COL_VNCATCLIENTES_CALLEDOMICILIO = "calle_domicilio";
    public static final String COL_VNCATCLIENTES_COLONIA = "colonia";
    public static final String COL_VNCATCLIENTES_CODIGOPOSTAL = "codigo_postal";
    public static final String COL_VNCATCLIENTES_TELEFONOS = "telefonos";
    public static final String COL_VNCATCLIENTES_FAX = "fax";
    public static final String COL_VNCATCLIENTES_EMAIL = "email";
    public static final String COL_VNCATCLIENTES_DOMICILIOBODEGA = "domicilio_bodega";
    public static final String COL_VNCATCLIENTES_REPRESENTANTELEGAL = "representante_legal";
    public static final String COL_VNCATCLIENTES_ATENCIONVENTAS = "atencion_ventas";
    public static final String COL_VNCATCLIENTES_ATENCIONPAGOS = "atencion_pagos";
    public static final String COL_VNCATCLIENTES_CVELOCALIDAD = "cve_localidad";
    public static final String COL_VNCATCLIENTES_COMENTARIOS = "comentarios";
    public static final String COL_VNCATCLIENTES_CVEUSUARIOVENTAS = "cve_usuario_ventas";
    public static final String COL_VNCATCLIENTES_CONTRASENA = "contrasena";
    public static final String COL_VNCATCLIENTES_ENTRE = "entre";
    public static final String COL_VNCATCLIENTES_ENTRE2 = "entre2";
    public static final String COL_VNCATCLIENTES_ESTATUS = "estatus";
    public static final String COL_VNCATCLIENTES_DOMICILIOENTREGA = "domicilio_entrega";
    public static final String COL_VNCATCLIENTES_ULTIMAACTUALIZACION = "ultima_actualizacion";
    public static final String COL_VNCATCLIENTES_CVEUSUARIOACTUALIZACION = "cve_usuario_actualizacion";
    public static final String COL_VNCATCLIENTES_LOCALIZACION = "localizacion";
    public static final String COL_VNCATCLIENTES_MOROSO = "moroso";
    public static final String COL_VNCATCLIENTES_COMENTARIOSCLIENTE = "comentarios_cliente";
    public static final String COL_VNCATCLIENTES_HISTORIALCOMENTARIOS = "historial_comentarios";
    public static final String COL_VNCATCLIENTES_OPINION_SERVICIO = "opinion_servicio";
    public static final String COL_VNCATCLIENTES_COMENTARIOS_SERVICIO = "comentarios_servicio";
    public static final String COL_VNCATCLIENTES_FECHAREGISTRO = "fecha_registro";
    public static final String COL_VNCATCLIENTES_ESTATUSMOVIMIENTOS = "estatus_movimientos";

    // in_cat_productos:
    public static final String COL_INCATPRODUCTOS_ID = "_id";
    public static final String COL_INCATPRODUCTOS_CVECATPRODUCTO = "cve_cat_producto";
    public static final String COL_INCATPRODUCTOS_CVECOMPANIA = "cve_compania";
    public static final String COL_INCATPRODUCTOS_CVE_GRUPO = "cve_grupo";
    public static final String COL_INCATPRODUCTOS_CVESUBGRUPO = "cve_subgrupo";
    public static final String COL_INCATPRODUCTOS_CVE_FAMILIA = "cve_familia";
    public static final String COL_INCATPRODUCTOS_CVEPRODUCTO = "cve_producto";
    public static final String COL_INCATPRODUCTOS_ORIGEN = "origen";
    public static final String COL_INCATPRODUCTOS_NOMPRODUCTO = "nom_producto";
    public static final String COL_INCATPRODUCTOS_CVEPOSICIONFINANCIERA = "cve_posicion_financiera";
    public static final String COL_INCATPRODUCTOS_CVECENTROCOSTOELABORA = "cve_centro_costo_elabora";
    public static final String COL_INCATPRODUCTOS_CVECLASIFICACION = "cve_clasificacion";
    public static final String COL_INCATPRODUCTOS_CVEUNIDADMEDIDA = "cve_unidad_medida";
    public static final String COL_INCATPRODUCTOS_CVEPROVEEDORPREFERENTE = "cve_proveedor_preferente";
    public static final String COL_INCATPRODUCTOS_ULTIMOCOSTO = "ultimo_costo";
    public static final String COL_INCATPRODUCTOS_COSTOPROMEDIO = "costo_promedio";
    public static final String COL_INCATPRODUCTOS_COSTOESTANDAR = "costo_estandar";
    public static final String COL_INCATPRODUCTOS_PRECIOUNITARIO = "precio_unitario";
    public static final String COL_INCATPRODUCTOS_PORCENTAJEIMPUESTO = "porcentaje_impuesto";
    public static final String COL_INCATPRODUCTOS_PIEZASPORPAQUETE = "piezas_por_paquete";
    public static final String COL_INCATPRODUCTOS_VENTAMINIMA = "venta_minima";
    public static final String COL_INCATPRODUCTOS_PIEZASPORLOTE = "piezas_por_lote";
    public static final String COL_INCATPRODUCTOS_MINPIEZASPORLOTE = "min_piezas_por_lote";
    public static final String COL_INCATPRODUCTOS_DIASFABRICACION = "dias_fabricacion";
    public static final String COL_INCATPRODUCTOS_PESOUNITARIO = "peso_unitario";
    public static final String COL_INCATPRODUCTOS_ESVENTA = "es_venta";
    public static final String COL_INCATPRODUCTOS_CONSIDERARMARGEN = "considerar_margen";
    public static final String COL_INCATPRODUCTOS_ESTATUS = "estatus";
    public static final String COL_INCATPRODUCTOS_CODIGOBARRASJPG = "codigo_barras_jpg";
    public static final String COL_INCATPRODUCTOS_CODIGOBARRASCHR = "codigo_barras_chr";
    public static final String COL_INCATPRODUCTOS_MINIMO = "minimo";
    public static final String COL_INCATPRODUCTOS_MAXIMO = "maximo";
    public static final String COL_INCATPRODUCTOS_REORDEN = "reorden";
    public static final String COL_INCATPRODUCTOS_COMENTARIOS = "comentarios";
    public static final String COL_INCATPRODUCTOS_USOS = "usos";
    public static final String COL_INCATPRODUCTOS_DOSIS = "dosis";
    public static final String COL_INCATPRODUCTOS_VENTAJAS = "ventajas";
    public static final String COL_INCATPRODUCTOS_FORMULA = "formula";
    public static final String COL_INCATPRODUCTOS_IMAGEN = "imagen";
    public static final String COL_INCATPRODUCTOS_PIEZASLOGIS = "piezas_logis";
    public static final String COL_INCATPRODUCTOS_ABREVIATURA = "abreviatura";
    public static final String COL_INCATPRODUCTOS_IEPS = "ieps";
    public static final String COL_INCATPRODUCTOS_INDICACIONES = "indicaciones";
    public static final String COL_INCATPRODUCTOS_ESPECIESCULTIVOS = "especies_cultivos";
    public static final String COL_INCATPRODUCTOS_IDCATEGORIA = "id_categoria";
    public static final String COL_INCATPRODUCTOS_IMAGENENCABEZADO = "imagen_encabezado";

    // vn_cat_conductos:
    public static final String COL_VNCATCONDUCTOS_ID = "_id";
    public static final String COL_VNCATCONDUCTOS_CVECONDUCTO = "cve_conducto";
    public static final String COL_VNCATCONDUCTOS_CVE_COMPANIA = "cve_compania";
    public static final String COL_VNCATCONDUCTOS_NOMBRECONDUCTO = "nombre_conducto";
    public static final String COL_VNCATCONDUCTOS_MOSTRAR = "mostrar";
    public static final String COL_VNCATCONDUCTOS_CONTRATO = "contrato";
    public static final String COL_VNCATCONDUCTOS_CONCEPTO = "concepto";

    // vn_pedidos_encabezado:
    public static final String COL_VNPEDIDOSENCABEZADO_ID = "_id";
    public static final String COL_VNPEDIDOSENCABEZADO_CVECOMPANIA = "cve_compania";
    public static final String COL_VNPEDIDOSENCABEZADO_NUMPEDIDO = "num_pedido";
    public static final String COL_VNPEDIDOSENCABEZADO_NUM_ANEXO = "num_anexo";
    public static final String COL_VNPEDIDOSENCABEZADO_TIPOPEDIDO = "tipo_pedido";
    public static final String COL_VNPEDIDOSENCABEZADO_ESTATUS = "estatus";
    public static final String COL_VNPEDIDOSENCABEZADO_FECHAPEDIDO = "fecha_pedido";
    public static final String COL_VNPEDIDOSENCABEZADO_CVEMONEDA = "cve_moneda";
    public static final String COL_VNPEDIDOSENCABEZADO_CVECLIENTE = "cve_cliente";
    public static final String COL_VNPEDIDOSENCABEZADO_CVEAGENTE = "cve_agente";
    public static final String COL_VNPEDIDOSENCABEZADO_CVEUSUARIOCAPTURA = "cve_usuario_captura";
    public static final String COL_VNPEDIDOSENCABEZADO_FECHAREQUERIMIENTO = "fecha_requerimiento";
    public static final String COL_VNPEDIDOSENCABEZADO_SUMA = "suma";
    public static final String COL_VNPEDIDOSENCABEZADO_DESCUENTO = "descuento";
    public static final String COL_VNPEDIDOSENCABEZADO_SUBTOTAL = "subtotal";
    public static final String COL_VNPEDIDOSENCABEZADO_IMPUESTO = "impuesto";
    public static final String COL_VNPEDIDOSENCABEZADO_TOTAL = "total";
    public static final String COL_VNPEDIDOSENCABEZADO_COMENTARIOS = "comentarios";
    public static final String COL_VNPEDIDOSENCABEZADO_COMENTARIOSCXC = "comentarios_cxc";
    public static final String COL_VNPEDIDOSENCABEZADO_ASISTENCIA = "asistencia";
    public static final String COL_VNPEDIDOSENCABEZADO_TOTALIEPS = "total_ieps";
    public static final String COL_VNPEDIDOSENCABEZADO_IEPS3 = "ieps_3";
    public static final String COL_VNPEDIDOSENCABEZADO_IEPS35 = "ieps_3_5";
    public static final String COL_VNPEDIDOSENCABEZADO_FAMILIA = "familia";
    public static final String COL_VNPEDIDOSENCABEZADO_VERIFICADO = "verificado";
    public static final String COL_VNPEDIDOSENCABEZADO_CVEUSUARIOVERIFICADO = "cve_usuario_verificado";
    public static final String COL_VNPEDIDOSENCABEZADO_FECHAVERIFICACION = "fecha_verificacion";
    public static final String COL_VNPEDIDOSENCABEZADO_FECHAAUTORIZACION = "fecha_autorización";
    public static final String COL_VNPEDIDOSENCABEZADO_COMENTARIOSLIB = "comentarios_lib";
    public static final String COL_VNPEDIDOSENCABEZADO_PORCENTAJEGENERAL = "porcentaje_general";
    public static final String COL_VNPEDIDOSENCABEZADO_PEDIDOMESANTERIOR = "pedido_mes_anterior";
    public static final String COL_VNPEDIDOSENCABEZADO_SURTIDO = "surtido";
    public static final String COL_VNPEDIDOSENCABEZADO_COMENTARIOSSURTIDO = "comentarios_surtido";
    public static final String COL_VNPEDIDOSENCABEZADO_USUARIOSURTIO = "usuario_surtio";
    public static final String COL_VNPEDIDOSENCABEZADO_FECHASURTIDO = "fecha_surtido";
    public static final String COL_VNPEDIDOSENCABEZADO_IMPRESO = "impreso";
    public static final String COL_VNPEDIDOSENCABEZADO_LATITUDE = "latitude";
    public static final String COL_VNPEDIDOSENCABEZADO_LONGITUDE = "longitude";
    public static final String COL_VNPEDIDOSENCABEZADO_FECHADEPAGO = "fecha_de_pago";


    // vn_pedidos_partidas:
    public static final String COL_VNPEDIDOSPARTIDAS_ID = "_id";
    public static final String COL_VNPEDIDOSPARTIDAS_CVECOMPANIA = "cve_compania";
    public static final String COL_VNPEDIDOSPARTIDAS_NUMPEDIDO = "num_pedido";
    public static final String COL_VNPEDIDOSPARTIDAS_NUMPARTIDA = "num_partida";
    public static final String COL_VNPEDIDOSPARTIDAS_CVECATPRODUCTO = "cve_cat_producto";
    public static final String COL_VNPEDIDOSPARTIDAS_CANTIDAD = "cantidad";
    public static final String COL_VNPEDIDOSPARTIDAS_CANTIDADENTREGADA = "cantidad_entregada";
    public static final String COL_VNPEDIDOSPARTIDAS_PRECIOUNITARIO = "precio_unitario";
    public static final String COL_VNPEDIDOSPARTIDAS_PORCENTAJEDESCUENTO = "porcentaje_descuento";
    public static final String COL_VNPEDIDOSPARTIDAS_PORCENTAJEIMPUESTO = "porcentaje_impuesto";
    public static final String COL_VNPEDIDOSPARTIDAS_PORCENTAJECOMISION = "porcentaje_comision";
    public static final String COL_VNPEDIDOSPARTIDAS_SUMA = "suma";
    public static final String COL_VNPEDIDOSPARTIDAS_DESCUENTO = "descuento";
    public static final String COL_VNPEDIDOSPARTIDAS_SUBTOTAL = "subtotal";
    public static final String COL_VNPEDIDOSPARTIDAS_IMPUESTO = "impuesto";
    public static final String COL_VNPEDIDOSPARTIDAS_TOTAL = "total";
    public static final String COL_VNPEDIDOSPARTIDAS_DOCUMENTOREFERENCIA = "documento_referencia";
    public static final String COL_VNPEDIDOSPARTIDAS_TIPODOCUMENTOREFERENCIA = "tipo_documento_referencia";
    public static final String COL_VNPEDIDOSPARTIDAS_PARTIDADOCUMENTOREFERENCIA = "partida_documento_referencia";
    public static final String COL_VNPEDIDOSPARTIDAS_CVECENTROCOSTO = "cve_centro_costo";
    public static final String COL_VNPEDIDOSPARTIDAS_PLANVENTAS = "plan_ventas";
    public static final String COL_VNPEDIDOSPARTIDAS_NUMPAQUTE = "num_paquete";
    public static final String COL_VNPEDIDOSPARTIDAS_CONSIDERARBACKORDER = "considerar_backorder";
    public static final String COL_VNPEDIDOSPARTIDAS_MEMBACKORDER = "mem_backorder";
    public static final String COL_VNPEDIDOSPARTIDAS_CVECONDUCTO = "cve_conducto";
    public static final String COL_VNPEDIDOSPARTIDAS_TIPOCONDUCTO = "tipo_conducto";
    public static final String COL_VNPEDIDOSPARTIDAS_ESTATUS = "estatus";
    public static final String COL_VNPEDIDOSPARTIDAS_PORCENTAJECOMISIONPROMOTOR = "porcentaje_comision_promotor";
    public static final String COL_VNPEDIDOSPARTIDAS_IEPS = "ieps";
    public static final String COL_VNPEDIDOSPARTIDAS_ESPAQUETE = "es_paquete";
    public static final String COL_VNPEDIDOSPARTIDAS_DESCRIPCIONCOMPLEMENTARIA = "descripcion_complementaria";


    // gl_accesos:
    public static final String COL_GLACCESOS_ID = "_id";
    public static final String COL_GLACCESOS_CVEUSUARIO = "cve_usuario";
    public static final String COL_GLACCESOS_PASSWORD = "password";
    public static final String COL_GLACCESOS_TIPOUSUARIO = "tipo_usuario";
    public static final String COL_GLACCESOS_ESTATUS = "estatus";
    public static final String COL_GLACCESOS_ACTUALIZO_PASSWORD = "actualizo_password";
    public static final String COL_GLACCESOS_ULTIMAACTUALIZACION = "ultima_actualizacion";
    public static final String COL_GLACCESOS_IMEI = "imei";

    // gl_sync:
    public static final String COL_GLSYNC_ID = "_id";
    public static final String COL_GLSYNC_CVEUSUARIO = "cve_usuario";
    public static final String COL_GLSYNC_FECHASYNC = "fecha_sync";
    public static final String COL_GLSYNC_EXITOSYNC = "exito_sync";


    // En el contexto de la clase genera la Base de datos verificando la versión. En el caso de la base creada con
    // Esta versión ya registrada no efectua operacion ninguna
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    // Se ajusta el onCreate para ejecutar los querys de creación de las tablas
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Bloque TABLE_VN_CAT_CLIENTES = "vn_cat_clientes"
        String CREATE_VN_CAT_CLIENTES_TABLE = "CREATE TABLE " + TABLE_VN_CAT_CLIENTES + "(" +
                COL_VNCATCLIENTES_ID + " INTEGER PRIMARY KEY, " +
                COL_VNCATCLIENTES_CVEUSUARIO + " TEXT, " +
                COL_VNCATCLIENTES_NOMBRE + " TEXT, " +
                COL_VNCATCLIENTES_RFC + " TEXT, " +
                COL_VNCATCLIENTES_TIPOCONTRIBUYENTE + " TEXT, " +
                COL_VNCATCLIENTES_CALLEDOMICILIO + " TEXT, " +
                COL_VNCATCLIENTES_COLONIA + " TEXT, " +
                COL_VNCATCLIENTES_CODIGOPOSTAL + " TEXT, " +
                COL_VNCATCLIENTES_TELEFONOS + " TEXT, " +
                COL_VNCATCLIENTES_FAX + " TEXT, " +
                COL_VNCATCLIENTES_EMAIL + " TEXT, " +
                COL_VNCATCLIENTES_DOMICILIOBODEGA + " TEXT, " +
                COL_VNCATCLIENTES_REPRESENTANTELEGAL  + " TEXT, " +
                COL_VNCATCLIENTES_ATENCIONVENTAS + " TEXT, " +
                COL_VNCATCLIENTES_ATENCIONPAGOS + " TEXT, " +
                COL_VNCATCLIENTES_CVELOCALIDAD + " INTEGER, " +
                COL_VNCATCLIENTES_COMENTARIOS + " BLOB, " +
                COL_VNCATCLIENTES_CVEUSUARIOVENTAS + " TEXT, " +
                COL_VNCATCLIENTES_CONTRASENA + " BLOB, " +
                COL_VNCATCLIENTES_ENTRE + " TEXT, " +
                COL_VNCATCLIENTES_ENTRE2 + " TEXT, " +
                COL_VNCATCLIENTES_ESTATUS + " INTEGER, " +
                COL_VNCATCLIENTES_DOMICILIOENTREGA + " TEXT, " +
                COL_VNCATCLIENTES_ULTIMAACTUALIZACION + " TEXT, " +
                COL_VNCATCLIENTES_CVEUSUARIOACTUALIZACION + " TEXT, " +
                COL_VNCATCLIENTES_LOCALIZACION + " TEXT, " +
                COL_VNCATCLIENTES_MOROSO + " INTEGER, " +
                COL_VNCATCLIENTES_COMENTARIOSCLIENTE + " TEXT, " +
                COL_VNCATCLIENTES_HISTORIALCOMENTARIOS + " TEXT, " +
                COL_VNCATCLIENTES_OPINION_SERVICIO + " TEXT, " +
                COL_VNCATCLIENTES_COMENTARIOS_SERVICIO + " TEXT, " +
                COL_VNCATCLIENTES_FECHAREGISTRO + " TEXT, " +
                COL_VNCATCLIENTES_ESTATUSMOVIMIENTOS + " TEXT " +
                ")";
        db.execSQL(CREATE_VN_CAT_CLIENTES_TABLE);

        // Bloque TABLE_IN_CAT_PRODUCTOS = "in_cat_productos"
        String CREATE_IN_CAT_PRODUCTOS_TABLE = "CREATE TABLE " + TABLE_IN_CAT_PRODUCTOS + "(" +
                COL_INCATPRODUCTOS_ID + " INTEGER PRIMARY KEY, " +
                COL_INCATPRODUCTOS_CVECATPRODUCTO + " INTEGER, " +
                COL_INCATPRODUCTOS_CVECOMPANIA + " TEXT, " +
                COL_INCATPRODUCTOS_CVE_GRUPO + " TEXT, " +
                COL_INCATPRODUCTOS_CVESUBGRUPO + " TEXT, " +
                COL_INCATPRODUCTOS_CVE_FAMILIA + " TEXT, " +
                COL_INCATPRODUCTOS_CVEPRODUCTO + " TEXT, " +
                COL_INCATPRODUCTOS_ORIGEN + " TEXT, " +
                COL_INCATPRODUCTOS_NOMPRODUCTO + " TEXT, " +
                COL_INCATPRODUCTOS_CVEPOSICIONFINANCIERA + " TEXT, " +
                COL_INCATPRODUCTOS_CVECENTROCOSTOELABORA + " TEXT, " +
                COL_INCATPRODUCTOS_CVECLASIFICACION + " TEXT, " +
                COL_INCATPRODUCTOS_CVEUNIDADMEDIDA + " TEXT, " +
                COL_INCATPRODUCTOS_CVEPROVEEDORPREFERENTE + " INTEGER, " +
                COL_INCATPRODUCTOS_ULTIMOCOSTO + " REAL, " +
                COL_INCATPRODUCTOS_COSTOPROMEDIO + " REAL, " +
                COL_INCATPRODUCTOS_COSTOESTANDAR + " REAL, " +
                COL_INCATPRODUCTOS_PRECIOUNITARIO + " REAL, " +
                COL_INCATPRODUCTOS_PORCENTAJEIMPUESTO + " REAL, " +
                COL_INCATPRODUCTOS_PIEZASPORPAQUETE + " INTEGER, " +
                COL_INCATPRODUCTOS_VENTAMINIMA + " INTEGER, " +
                COL_INCATPRODUCTOS_PIEZASPORLOTE + " INTEGER, " +
                COL_INCATPRODUCTOS_MINPIEZASPORLOTE + " INTEGER, " +
                COL_INCATPRODUCTOS_DIASFABRICACION + " INTEGER, " +
                COL_INCATPRODUCTOS_PESOUNITARIO + " REAL, " +
                COL_INCATPRODUCTOS_ESVENTA + " INTEGER, " +
                COL_INCATPRODUCTOS_CONSIDERARMARGEN + " INTEGER, " +
                COL_INCATPRODUCTOS_ESTATUS + " TEXT, " +
                COL_INCATPRODUCTOS_CODIGOBARRASJPG + " BLOB, " +
                COL_INCATPRODUCTOS_CODIGOBARRASCHR + " TEXT, " +
                COL_INCATPRODUCTOS_MINIMO + " REAL, " +
                COL_INCATPRODUCTOS_MAXIMO + " REAL, " +
                COL_INCATPRODUCTOS_REORDEN + " REAL, " +
                COL_INCATPRODUCTOS_COMENTARIOS + " TEXT, " +
                COL_INCATPRODUCTOS_USOS + " TEXT, " +
                COL_INCATPRODUCTOS_DOSIS + " BLOB, " +
                COL_INCATPRODUCTOS_VENTAJAS + " TEXT, " +
                COL_INCATPRODUCTOS_FORMULA + " BLOB, " +
                COL_INCATPRODUCTOS_IMAGEN + " TEXT, " +
                COL_INCATPRODUCTOS_PIEZASLOGIS + " INTEGER, " +
                COL_INCATPRODUCTOS_ABREVIATURA + " TEXT, " +
                COL_INCATPRODUCTOS_IEPS + " TEXT, " +
                COL_INCATPRODUCTOS_INDICACIONES + " BLOB, " +
                COL_INCATPRODUCTOS_ESPECIESCULTIVOS + " TEXT, " +
                COL_INCATPRODUCTOS_IDCATEGORIA + " INTEGER, " +
                COL_INCATPRODUCTOS_IMAGENENCABEZADO + " TEXT " +
                ")";
        db.execSQL(CREATE_IN_CAT_PRODUCTOS_TABLE);

        // Bloque:  TABLE_VN_CAT_CONDUCTOS = "vn_cat_conductos"
        String CREATE_VN_CAT_CONDUCTOS_TABLE = "CREATE TABLE " + TABLE_VN_CAT_CONDUCTOS + "(" +
                COL_VNCATCONDUCTOS_ID + " INTEGER PRIMARY KEY, " +
                COL_VNCATCONDUCTOS_CVECONDUCTO + " INTEGER, " +
                COL_VNCATCONDUCTOS_CVE_COMPANIA + " TEXT, " +
                COL_VNCATCONDUCTOS_NOMBRECONDUCTO + " TEXT, " +
                COL_VNCATCONDUCTOS_MOSTRAR + " INTEGER, " +
                COL_VNCATCONDUCTOS_CONTRATO + " INTEGER, " +
                COL_VNCATCONDUCTOS_CONCEPTO + " INTEGER " +
                ")";
        db.execSQL(CREATE_VN_CAT_CONDUCTOS_TABLE);

        // Bloque: TABLE_VN_PEDIDOS_ENCABEZADO = "vn_pedidos_encabezado"
        String CREATE_VN_PEDIDOS_ENCABEZADO_TABLE = "CREATE TABLE " + TABLE_VN_PEDIDOS_ENCABEZADO + "(" +
                COL_VNPEDIDOSENCABEZADO_ID + " INTEGER PRIMARY KEY, " +
                COL_VNPEDIDOSENCABEZADO_CVECOMPANIA + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_NUMPEDIDO + " INTEGER, " +
                COL_VNPEDIDOSENCABEZADO_NUM_ANEXO + " INTEGER, " +
                COL_VNPEDIDOSENCABEZADO_TIPOPEDIDO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_ESTATUS + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FECHAPEDIDO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_CVEMONEDA + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_CVECLIENTE + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_CVEAGENTE + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_CVEUSUARIOCAPTURA + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FECHAREQUERIMIENTO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_SUMA + " REAL, " +
                COL_VNPEDIDOSENCABEZADO_DESCUENTO + " REAL, " +
                COL_VNPEDIDOSENCABEZADO_SUBTOTAL + " REAL, " +
                COL_VNPEDIDOSENCABEZADO_IMPUESTO + " REAL, " +
                COL_VNPEDIDOSENCABEZADO_TOTAL + " REAL, " +
                COL_VNPEDIDOSENCABEZADO_COMENTARIOS + " BLOB, " +
                COL_VNPEDIDOSENCABEZADO_COMENTARIOSCXC + " BLOB, " +
                COL_VNPEDIDOSENCABEZADO_ASISTENCIA + " INTEGER, " +
                COL_VNPEDIDOSENCABEZADO_TOTALIEPS + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_IEPS3 + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_IEPS35 + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FAMILIA + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_VERIFICADO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_CVEUSUARIOVERIFICADO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FECHAVERIFICACION + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FECHAAUTORIZACION + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_COMENTARIOSLIB + " BLOB, " +
                COL_VNPEDIDOSENCABEZADO_PORCENTAJEGENERAL + " REAL, " +
                COL_VNPEDIDOSENCABEZADO_PEDIDOMESANTERIOR + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_SURTIDO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_COMENTARIOSSURTIDO + " BLOB, " +
                COL_VNPEDIDOSENCABEZADO_USUARIOSURTIO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FECHASURTIDO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_IMPRESO + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_LATITUDE + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_LONGITUDE + " TEXT, " +
                COL_VNPEDIDOSENCABEZADO_FECHADEPAGO + " TEXT " +
                ")";
        db.execSQL(CREATE_VN_PEDIDOS_ENCABEZADO_TABLE);

        // Bloque: TABLE_VN_PEDIDOS_PARTIDAS = "vn_pedidos_partidas"
        String CREATE_VN_PEDIDOS_PARTIDAS_TABLE = "CREATE TABLE " + TABLE_VN_PEDIDOS_PARTIDAS + "(" +
                COL_VNPEDIDOSPARTIDAS_ID + " INTEGER PRIMARY KEY, " +
                COL_VNPEDIDOSPARTIDAS_CVECOMPANIA + " TEXT, " +
                COL_VNPEDIDOSPARTIDAS_NUMPEDIDO + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_NUMPARTIDA + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_CVECATPRODUCTO + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_CANTIDAD + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_CANTIDADENTREGADA + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_PRECIOUNITARIO + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_PORCENTAJEDESCUENTO + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_PORCENTAJEIMPUESTO + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_PORCENTAJECOMISION + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_SUMA + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_DESCUENTO + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_SUBTOTAL + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_IMPUESTO + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_TOTAL + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_DOCUMENTOREFERENCIA + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_TIPODOCUMENTOREFERENCIA + " TEXT, " +
                COL_VNPEDIDOSPARTIDAS_PARTIDADOCUMENTOREFERENCIA + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_CVECENTROCOSTO + " TEXT, " +
                COL_VNPEDIDOSPARTIDAS_PLANVENTAS + " TEXT, " +
                COL_VNPEDIDOSPARTIDAS_NUMPAQUTE + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_CONSIDERARBACKORDER + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_MEMBACKORDER + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_CVECONDUCTO + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_TIPOCONDUCTO + " TEXT, " +
                COL_VNPEDIDOSPARTIDAS_ESTATUS + " TEXT, " +
                COL_VNPEDIDOSPARTIDAS_PORCENTAJECOMISIONPROMOTOR + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_IEPS + " REAL, " +
                COL_VNPEDIDOSPARTIDAS_ESPAQUETE + " INTEGER, " +
                COL_VNPEDIDOSPARTIDAS_DESCRIPCIONCOMPLEMENTARIA + " TEXT " +
                ")";
        db.execSQL(CREATE_VN_PEDIDOS_PARTIDAS_TABLE);

        // Bloque: TABLE_GL_ACCESOS = "gl_accesos"
        String CREATE_GL_ACCESOS_TABLE = "CREATE TABLE " + TABLE_GL_ACCESOS + "(" +
                COL_GLACCESOS_ID + " INTEGER PRIMARY KEY, " +
                COL_GLACCESOS_CVEUSUARIO + " TEXT, " +
                COL_GLACCESOS_PASSWORD + " BLOB, " +
                COL_GLACCESOS_TIPOUSUARIO + " TEXT, " +
                COL_GLACCESOS_ESTATUS + " INTEGER, " +
                COL_GLACCESOS_ACTUALIZO_PASSWORD + " INTEGER, " +
                COL_GLACCESOS_ULTIMAACTUALIZACION + " TEXT, " +
                COL_GLACCESOS_IMEI + " TEXT " +
                ")";
        db.execSQL(CREATE_GL_ACCESOS_TABLE);

        // Bloque para la tabla local GL_SYNC
        String CREATE_GL_SYNC_TABLE = "CREATE TABLE " + TABLE_GL_SYNC + "(" +
                COL_GLSYNC_ID + " INTEGER PRIMARY KEY, " +
                COL_GLSYNC_CVEUSUARIO + " TEXT, " +
                COL_GLSYNC_FECHASYNC + " TEXT, " +
                COL_GLSYNC_EXITOSYNC + " INTEGER " +
                ")";
        db.execSQL(CREATE_GL_SYNC_TABLE);
    } // Cierre del onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // OJO!!! Esta rutina es especìfica para la acción de actualización de BD. Hoy solo elimina y regenera la BD X cambio de versión
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VN_CAT_CLIENTES);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IN_CAT_PRODUCTOS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VN_CAT_CONDUCTOS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VN_PEDIDOS_ENCABEZADO);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VN_PEDIDOS_PARTIDAS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GL_ACCESOS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GL_SYNC);
        onCreate(db);
    }

    // Al colocarse al inicio del código provoca y forza la creación física de la BD así como sus tablas.
    public void checkDBStatus()
    {
        String query = "SELECT * FROM " + TABLE_GL_SYNC;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();
        db.close();
    }

    // Metodo que es usado para obtener los datos de los clientes de la tabla vn_cat_clientes los coloca en un array y los regresa en la función.
    public String[] getClientes()
    {
        Cursor cursor = getReadableDatabase().rawQuery("select '[' || cve_usuario || '] ' || nombre as cliente from vn_cat_clientes;", null);
        cursor.moveToFirst();
        ArrayList<String> nombreCliente = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            nombreCliente.add(cursor.getString(cursor.getColumnIndex("cliente")));
            cursor.moveToNext();
        }
        cursor.close();
        return nombreCliente.toArray(new String[nombreCliente.size()]);
    }


} // FIn de la definición clase: MyDBHandler
