package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by william_lee on 8/29/16.
 */

//TODO: 5,105,043 edges
//TODO: 875,714 vertex
public class k {

    private static final int NUMN=875714;
    private static int ft=0;
    private static int[]nnum;//arr[finish_time]=node#
    private static boolean[]v;
    private static int[]leadarr;

    private static HashSet<Integer>[]fwd;
    private static HashSet<Integer>[]bck;


    public static void main(String[]args)throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));


        fwd = new HashSet[NUMN];
        bck = new HashSet[NUMN];

        String s;
        String[]split;
        int a,b;
        while((s=in.readLine())!=null){
            split=s.split(" ");
            a=Integer.parseInt(split[0])-1;
            b = Integer.parseInt(split[1])-1;
            if(fwd[a]==null){
                fwd[a]=new HashSet<Integer>();
            }
            fwd[a].add(b);
            if(bck[b]==null){
                bck[b]=new HashSet<Integer>();
            }
            bck[b].add(a);
        }

        System.out.println("entered");
        //1st use bck
        nnum=new int[NUMN];
        for(int i=0;i<NUMN;i++){nnum[i]=-1;}
        v=new boolean[NUMN];
        for(int i=NUMN-1;i>=0;i--){
            if(!v[i]){
                dfs1(i);
            }
        }
        System.out.println("finish stack1");
        //TODO: nnum !right

        v=new boolean[NUMN];
        leadarr=new int[NUMN];
        for(int f=NUMN-1;f>=0;f--){
            if(!v[nnum[f]]){
                dfs2(nnum[f], nnum[f]);
            }
        }
        int[]counts=new int[NUMN];
        for(int i=0;i<NUMN;i++){
            counts[leadarr[i]]++;
        }
        Arrays.sort(counts);
        for(int i=counts.length-1;i>=counts.length-5;i--){
            System.out.println(counts[i]);
        }
    }

//    public static void main2(String[]args){
//
//        Scanner in = new Scanner(System.in);
//
//        fwd = new HashSet[NUMN];
//        bck = new HashSet[NUMN];
//
//        String s;
//        String[]split;
//        int a,b;
//        while((s=in.nextLine()).length()>0){
//            split=s.split(" ");
//            a=Integer.parseInt(split[0])-1;
//            b = Integer.parseInt(split[1])-1;
//            if(fwd[a]==null){
//                fwd[a]=new HashSet<Integer>();
//            }
//            fwd[a].add(b);
//            if(bck[b]==null){
//                bck[b]=new HashSet<Integer>();
//            }
//            bck[b].add(a);
//        }
//
//        System.out.println("entered");
//        //1st use bck
//        nnum=new int[NUMN];
//        for(int i=0;i<NUMN;i++){nnum[i]=-1;}
//        v=new boolean[NUMN];
//        for(int i=NUMN-1;i>=0;i--){
//            if(!v[i]){
//                dfs1(i);
//            }
//        }
//        System.out.println("finish stack1");
//        //TODO: nnum !right
//
//        v=new boolean[NUMN];
//        leadarr=new int[NUMN];
//        for(int f=NUMN-1;f>=0;f--){
//            if(!v[nnum[f]]){
//                dfs2(nnum[f], nnum[f]);
//            }
//        }
//        int[]counts=new int[NUMN];
//        for(int i=0;i<NUMN;i++){
//            counts[leadarr[i]]++;
//        }
//        Arrays.sort(counts);
//        for(int i=counts.length-1;i>=counts.length-5;i--){
//            System.out.println(counts[i]);
//        }
//    }

    public static void dfs2(int n, int lead) {
        v[n]=true;
        leadarr[n]=lead;
        if(fwd[n]!=null){
            for(Integer neigh:fwd[n]){
                if(!v[neigh]){
                    dfs2(neigh,lead);
                }
            }
        }
    }

//        Stack<Integer>st=new Stack<>();
//        st.push(n);
//        int node;
//        while(!st.isEmpty()){
//            node=st.pop();
//            if(!v[node]){
//                v[node]=true;
//                leadarr[node]=lead;
//                if(fwd[node]!=null){
//                    for(Integer neigh:fwd[node]){
//                        if(!v[neigh]){
//                            st.push(neigh);
//                        }
//                    }
//                }
//            }


    public static void dfs1(int n) {
        v[n] = true;
        if (bck[n] != null) {
            for (Integer neigh : bck[n]) {
                if (!v[neigh]) {
                    dfs1(neigh);
                }
            }
        }
        nnum[ft]=n;
        ft++;
    }
//        Stack<c>st=new Stack<>();
//        st.push(new c(n,0));
//        v[n]=true;
//        c get;
//        while(!st.empty()){
//            get=st.pop();
//            int ind=get.ind;
//            if(bck[get.node]!=null){
//                int i;
//                for(i=ind;i<bck[get.node].size();i++){
//                    if(!v[(int)(bck[get.node].toArray()[i])]){
//                        v[(int)(bck[get.node].toArray()[i])]=true;
//                        st.push(new c(get.node,ind+1));
//                        st.push(new c((int)(bck[get.node].toArray()[i]),0));
//                        break;
//                    }
//                }
//                ind=i;
//            }
//            if(bck[get.node]==null||ind==bck[get.node].size()){
//                //System.out.println(get.node);
//                ft++;
//                nnum[ft]=get.node;
//
//            }
//        }


}
//class c{
//    int node;
//    int ind;
//    public c(int node, int ind){
//        this.node=node;
//        this.ind=ind;
//    }
//}
