package br.edu.ifsp.dmos5.app13_listatarefas.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmos5.app13_listatarefas.model.dao.ITaskDao;
import br.edu.ifsp.dmos5.app13_listatarefas.model.dao.TaskDaoSingleton;
import br.edu.ifsp.dmos5.app13_listatarefas.model.entities.Task;
import br.edu.ifsp.dmos5.app13_listatarefas.mvp.MainMVP;
import br.edu.ifsp.dmos5.app13_listatarefas.utils.Constant;
import br.edu.ifsp.dmos5.app13_listatarefas.view.RecyclerViewItemClickListener;
import br.edu.ifsp.dmos5.app13_listatarefas.view.TaskDetailsActivity;
import br.edu.ifsp.dmos5.app13_listatarefas.view.adapter.ItemPocketRecyclerAdapter;

public class MainPresenter implements MainMVP.Presenter{

    private MainMVP.View view;
    private ITaskDao dao;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TaskDaoSingleton.getInstance();
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), TaskDetailsActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails(Task task) {
        Intent intent = new Intent(view.getContext(), TaskDetailsActivity.class);
        intent.putExtra(Constant.ATTR_TITLE, task.getTitle());
        view.getContext().startActivity(intent);
    }

    @Override
    public void deleteTask(Task task){
        dao.delete(task);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {

        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void favoriteTask(Task task) {
        task.setFavorite(!task.isFavorite());
        dao.update(task.getTitle(), task);

        if (task.isFavorite()){
            dao.insertStart(task);
        }
        else {
            dao.insertEnd(task);
        }
    }
}
