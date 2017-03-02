package com.example.yvanp.practica2_lista;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list;
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* String nombre = this.leeInfo( "Como te llamas?");
        this.aviso( nombre );*/

        this.list = new ArrayList<>();
        this.listAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                this.list
        );

        final ListView lvlist = (ListView) this.findViewById( R.id.lvlist );
        lvlist.setAdapter( this.listAdapter );

       lvlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
                    MainActivity.this.list.remove(position);
                    MainActivity.this.listAdapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });//pulsacion larga sobre un elem de la lista*/

        final Button btAdd = (Button) this.findViewById( R.id.btAdd );
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // MainActivity.this.listAdapter.add( "hola" );
                final EditText edItem = new EditText( MainActivity.this );
                AlertDialog.Builder dlg = new AlertDialog.Builder( MainActivity.this );
                dlg.setTitle( "nuevo elemento" );
                dlg.setView( edItem );
                dlg.setPositiveButton("+", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.inserta( edItem.getText().toString() );
                    }
                });
                dlg.create().show();
            }
        });

        this.registerForContextMenu( lvlist );


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo cmi)
    {
        super.onCreateContextMenu(menu, view, cmi);

        if( view.getId() == R.id.lvlist )
        {
            this.getMenuInflater().inflate( R.menu.contextual_menu, menu );
            menu.setHeaderTitle( R.string.lblContextTit );
        }
    }

    @Override
    public boolean onContextItemSelected( MenuItem menuItem )
    {
        boolean toret = false;
        switch( menuItem.getItemId() )
        {
            case R.id.opElimina:
                toret = true;
               int pos =  ( (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo() ).position;

                this.elimina( pos );
                toret = true;
                break;
        }
        return toret;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.main_menu, menu );
        return  true;
    }


    public boolean onOptionsItemsSelected(MenuItem menuItem)
    {
        boolean toret = false;
        switch( menuItem.getItemId() )
        {
            case R.id.op_inserta: toret = true;
                this.inserta();
                break;
            case R.id.opElimina:
                if( this.list.size() > 0)
                {
                    elimina( this.list.size() - 1);
                    /*
                    this.list.remove( this.list.size() - 1 );
                    this.listAdapter.notifyDataSetChanged();*/
                    toret = true;
                }
                break;
        }
        return toret;
    }

    private void inserta()
    {
        final EditText edItem = new EditText( MainActivity.this );
        AlertDialog.Builder dlg = new AlertDialog.Builder( MainActivity.this );
        dlg.setTitle( "nuevo elemento" );
        dlg.setView( edItem );
        dlg.setPositiveButton("+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.inserta( edItem.getText().toString() );
            }
        });
        dlg.create().show();

    }


    private void elimina(int pos)
    {
            if (pos >= 0) {
                MainActivity.this.list.remove(pos);
                MainActivity.this.listAdapter.notifyDataSetChanged();
            }
    }
    private void inserta(String item){
       TextView tvNum = (TextView)  this.findViewById( R.id.tvNum );
        this.listAdapter.add( item );
        tvNum.setText( Integer.toString( this.listAdapter.getCount() ));
    }

    private void aviso(String msg)
    {
        Toast.makeText( this , msg, Toast.LENGTH_LONG).show();
    }

    private void avisoB(String msg)
    {
        AlertDialog.Builder dlg = new  AlertDialog.Builder( this );

        dlg.setMessage( msg );
        dlg.create().show();
    }

    private void pregunta(String msg)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setMessage( msg );
        dlg.setPositiveButton( "Hola", null );
        dlg.setNegativeButton( "Adios", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit( 0 );
            }
        });
        dlg.create().show();
    }

    private String leeInfo(String datos)
    {
        final StringBuilder toret = new StringBuilder();
        final EditText edInfo = new EditText( this );
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setTitle( datos );
        dlg.setView( edInfo );
        dlg.setPositiveButton( "Cancel", null );
        dlg.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toret.append(edInfo.getText().toString());
                MainActivity.this.aviso( toret.toString() );
            }
        });
        dlg.create().show();
        return toret.toString();
    }
}
