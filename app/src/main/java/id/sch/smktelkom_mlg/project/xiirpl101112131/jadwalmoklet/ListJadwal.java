package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

/*
public class ListJadwal extends AppCompatActivity {
    ListView list_view;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> list_items = new ArrayList<String>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jadwal);

        list_view = (ListView) findViewById(R.id.list_view);
        list.add("ONE");
        list.add("TWO");
        list.add("THREE");
        list.add("FOUR");
        list.add("FIVE");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        list_view.setAdapter(adapter);

        list_view.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list_view.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                count = count + 1;
                actionMode.setTitle(count + " items selected");
                list_items.add(list.get(position));

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.my_context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.delete_id:
                        for (String msg : list_items) {
                            adapter.remove(msg);
                        }
                        Toast.makeText(getBaseContext(), count + " items removed", Toast.LENGTH_SHORT).show();
                        count = 0;
                        actionMode.finish();
                        //list_view.setSelected(true);
                        return true;
                    //break;
                    default:
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }
}
*/
