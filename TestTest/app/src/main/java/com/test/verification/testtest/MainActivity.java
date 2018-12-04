package com.test.verification.testtest;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SetGetHourPlan> arrayListHourPlan;
    ArrayList<SetGetPackages> arrayListPackages;
    ArrayList<SetGetService> arrayListHourServices;
    ArrayList<SetGetShifts> arrayListHourShifts;
    ArrayList<String> arrayListMain;
    ListView listView;
    ArrayAdapter arrayAdapter;
    SetGetHourPlan setGetHourPlan;
    SetGetPackages setGetPackages;
    SetGetShifts setGetShifts;
    SetGetService setGetService;
    int count = 0;
    double price,totalPrice,discount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        arrayListHourPlan = new ArrayList<>();
        arrayListHourServices = new ArrayList<>();
        arrayListHourShifts = new ArrayList<>();
        arrayListPackages = new ArrayList<>();
        arrayListMain = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayListMain);
        listView.setAdapter(arrayAdapter);
        new LongOperation().execute("");




    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setData();


        }

        @Override
        protected String doInBackground(String... params) {



            for (int i = 0; i < arrayListHourServices.size(); i++)
            {
                for (int j = 0; j < arrayListPackages.size(); j++)
                {
                    for (int z = 0; z < arrayListHourShifts.size(); z++)
                    {
                        for (int p = 0; p < arrayListHourPlan.size(); p++)
                        {
                            price=0.0;
                            totalPrice=0.0;
                            discount=0.0;
                             price=arrayListHourServices.get(i).getPrice() * arrayListHourPlan.get(p).getPrice();
                             totalPrice=price+(price*( arrayListHourShifts.get(z).getAddPrice()));
                             discount= arrayListPackages.get(j).getDiscount()
                                     +
                                     arrayListHourPlan.get(p).getDiscount() ;


                            arrayListMain.add(arrayListHourServices.get(i).getName()
                                    + "+" + arrayListPackages.get(j).getName()
                                    + "+" + arrayListHourShifts.get(z).getName()
                                    + "+" + arrayListHourPlan.get(p).getName()
                                    + "=" +

                                    ( String.format("%.2f", totalPrice - (totalPrice *discount))  ));
                       //     arrayListMain.add(String.valueOf(arrayListPackages.get(j).getDiscount())+"+"+String.valueOf(arrayListHourPlan.get(p).getDiscount() )+"="+   String.valueOf(discount));
                            count++;
                        }
                    }
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, String.valueOf(arrayListMain.size()), Toast.LENGTH_SHORT).show();
         //   Toast.makeText(MainActivity.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            arrayAdapter.notifyDataSetChanged();

        }
    }
    public void setData()
    {
        setGetHourPlan = new SetGetHourPlan();
        setGetHourPlan.setName("4h");
        setGetHourPlan.setDiscount(0);
        setGetHourPlan.setPrice(4);
        arrayListHourPlan.add(setGetHourPlan);


        setGetHourPlan = new SetGetHourPlan();
        setGetHourPlan.setName("6h");
        setGetHourPlan.setDiscount(0.05);
        setGetHourPlan.setPrice(6);
        arrayListHourPlan.add(setGetHourPlan);


        setGetHourPlan = new SetGetHourPlan();
        setGetHourPlan.setName("8h");
        setGetHourPlan.setDiscount(0.1);
        setGetHourPlan.setPrice(8);
        arrayListHourPlan.add(setGetHourPlan);


        setGetHourPlan = new SetGetHourPlan();
        setGetHourPlan.setName("10h");
        setGetHourPlan.setDiscount(0.15);
        setGetHourPlan.setPrice(10);
        arrayListHourPlan.add(setGetHourPlan);

        /////////////////////////////
        setGetPackages = new SetGetPackages();
        setGetPackages.setName("Daily");
        setGetPackages.setDiscount(0);
        arrayListPackages.add(setGetPackages);


        setGetPackages = new SetGetPackages();
        setGetPackages.setName("weekly");
        setGetPackages.setDiscount(0.1);
        arrayListPackages.add(setGetPackages);


        setGetPackages = new SetGetPackages();
        setGetPackages.setName("Monthly");
        setGetPackages.setDiscount(0.15);
        arrayListPackages.add(setGetPackages);

        //////////////////////////////////////

        setGetShifts = new SetGetShifts();
        setGetShifts.setName("morning");
        setGetShifts.setAddPrice(0);
        arrayListHourShifts.add(setGetShifts);

        setGetShifts = new SetGetShifts();
        setGetShifts.setName("afternoon");
        setGetShifts.setAddPrice(0.05);
        arrayListHourShifts.add(setGetShifts);


        //////////////////////////////////////

        setGetService = new SetGetService();
        setGetService.setName("House Keeping");
        setGetService.setPrice(5);
        arrayListHourServices.add(setGetService);

        setGetService = new SetGetService();

        setGetService.setName("Baby sitting");
        setGetService.setPrice(6);
        arrayListHourServices.add(setGetService);

        setGetService = new SetGetService();

        setGetService.setName("Nursing");
        setGetService.setPrice(6);
        arrayListHourServices.add(setGetService);

        setGetService = new SetGetService();
        setGetService.setName("Cleaning guy");
        setGetService.setPrice(4);
        arrayListHourServices.add(setGetService);
    }
}
