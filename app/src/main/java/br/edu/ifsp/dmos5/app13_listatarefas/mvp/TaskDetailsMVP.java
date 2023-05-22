package br.edu.ifsp.dmos5.app13_listatarefas.mvp;

import android.content.Context;
import android.os.Bundle;

public interface TaskDetailsMVP {

    interface View{
        Context getContext();
        void updateUI(String title, String description);
        Bundle getBundle();
        void showToast(String message);
        void close();
    }

    interface Presenter{
        void detach();
        void verifyUpdate();
        void saveTask(String title, String description);
    }
}
