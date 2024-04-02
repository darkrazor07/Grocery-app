package com.example.myshoppingapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myshoppingapp.activities.MainActivity;
import com.example.myshoppingapp.adapters.MyCartAdapter;
import com.example.myshoppingapp.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {
    RecyclerView recyclerView;
    List<MyCartModel> myCartModelList;

    MyCartAdapter cartAdapter;
    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView totalAmount;
    Button qr;


    public MyCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root=inflater.inflate(R.layout.fragment_mycarts, container, false);
       recyclerView=root.findViewById(R.id.recyclerView);
       qr=root.findViewById(R.id.qr_scan);
       qr.setOnClickListener(v -> {
           scanCode();
       });
       totalAmount=root.findViewById(R.id.textView6);
       LocalBroadcastManager.getInstance(getActivity()).registerReceiver(nMessageReceiver,new IntentFilter("totalAmount"));
       db=FirebaseFirestore.getInstance();
       auth=FirebaseAuth.getInstance();
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       myCartModelList=new ArrayList<>();
       cartAdapter=new MyCartAdapter(getActivity(),myCartModelList);
       recyclerView.setAdapter(cartAdapter);

       db.collection("AddToCart").document(auth.getCurrentUser().getUid())
               .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful()){
                           for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                               MyCartModel myCartModel=documentSnapshot.toObject(MyCartModel.class);
                               myCartModelList.add(myCartModel);
                               cartAdapter.notifyDataSetChanged();

                           }
                       }
                   }
               });



       return root;
    }

    private void scanCode() {
        ScanOptions options=new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setOrientationLocked(true);
        options.setBeepEnabled(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }
    ActivityResultLauncher<ScanOptions> barLauncher=registerForActivityResult(new ScanContract(),result->{
        if(result.getContents()!=null){
            AlertDialog.Builder builder= new AlertDialog.Builder(this.getContext());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
    });

    public BroadcastReceiver nMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill=intent.getIntExtra("totalBill",0);
            totalAmount.setText("TotalBill:"+totalBill+"$");
        }
    };
}