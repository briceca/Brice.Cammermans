package Data;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.brice.spaghettify.R;

import java.util.List;

public class ArtikelList extends ArrayAdapter {
    private Activity context;
    List<Artikel> artikels;

    public ArtikelList(Activity context, List<Artikel> artikels)
    {
        super(context, R.layout.layout_artikel_list, artikels);
        this.context = context;
        this.artikels = artikels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artikel_list, null, true);

        TextView textViewNaam = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrijs = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewType = (TextView) listViewItem.findViewById(R.id.textViewType);

        Artikel artikel = artikels.get(position);
        textViewNaam.setText(artikel.getNaam());
        textViewPrijs.setText(artikel.getPrijs().toString());
        textViewType.setText(artikel.getType().toString());

        return listViewItem;
    }
}
