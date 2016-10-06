package com.teca.dudu.triptogether.util;

import android.content.Context;

import com.teca.dudu.triptogether.dao.DespesaDao;
import com.teca.dudu.triptogether.model.Usuario;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tales on 04/10/16.
 */

public class DividasSolucao {
    private ArrayList<Usuario> users,pagantes,devedores;
    private float saldoPagantes[],saldoDevedores[];
    private String sSugestoes[];
    private DespesaDao despesaDao;
    public DividasSolucao(Context context, ArrayList<Usuario> users) {
        this.users = users;
        pagantes = new ArrayList<Usuario>();
        devedores = new ArrayList<Usuario>();
        despesaDao = new DespesaDao(context);
        saldoPagantes = new float[25];
        saldoDevedores = new float[25];
        sSugestoes = new String[24];
    }
    
    private void setDevedoresEPagantes(){
        int i=0,j=0;
        for(Iterator<Usuario> iter = users.iterator();iter.hasNext();) {
            Usuario user = iter.next();
            float saldo = despesaDao.saldoDoUsuario(user.get_id(),user.getIdViagem());

            if(saldo > 0){
                pagantes.add(user);
                saldoPagantes[i] = saldo; // i = posição do usuario no array list pagantes
                ++i;
            }
            else if(saldo < 0){
                devedores.add(user);
                saldoPagantes[j] = saldo; // j = posição do usuario no array list devedores
                ++j;
            }
        }
    }

    public ArrayList<String> getResultado(){
        setDevedoresEPagantes();

        int i = 0,indexReajuste = -1;
        float saldoReajuste = (float) 0.0;
        String nomeReajuste = new String();
        Usuario devedor = null;
        for(Iterator<Usuario> iter = pagantes.iterator(),iter2=devedores.iterator();iter.hasNext();) { //itera nos pagantes até ter pagantes restantes
            Usuario pagante = iter.next();

            if(i==0){
                devedor = iter2.next();
                //i=1;
            }

            while(saldoPagantes[pagantes.indexOf(pagante)] > 0){
                if (indexReajuste != -1) { //se o ultimo devedor não tiver pago tudo ainda
                                           // ele deve pagar para o proximo pagante e não trocar de devedor
                    System.out.println("foi");
                    devedor = iter2.next();//caso ele tiver pago passa pro proximo devedor
                    indexReajuste =-1;
                }

                System.out.println(saldoPagantes[pagantes.indexOf(pagante)]);
                System.out.println(saldoDevedores[devedores.indexOf(devedor)]);

                saldoPagantes[pagantes.indexOf(pagante)] +=     //decrementa do saldo do (iterador)pagante o saldo dos (iterador)devedores
                        saldoDevedores[devedores.indexOf(devedor)];


                System.out.println(saldoDevedores[devedores.indexOf(devedor)]);
                System.out.println(saldoPagantes[pagantes.indexOf(pagante)]);

                if(saldoPagantes[pagantes.indexOf(pagante)] >= 0){ //se o devedor tiver pago tudo o que devia cria a sugestão
                    sSugestoes[i] = devedor.getNome() + " paga " +
                            -1*saldoDevedores[devedores.indexOf(devedor)] + " para " + //Cria a string de sugestao
                            pagante.getNome();
                    ++i;
                    indexReajuste = 1; //faz o iterador de devedores passar para o proximo devedor
                }
                else if(saldoPagantes[pagantes.indexOf(pagante)] < 0){ //se o devedor tiver pago tudo para o pagante e ainda n tiver quitado as dividas ele paga o proximo pagante;
                    indexReajuste = -1;
                    saldoDevedores[devedores.indexOf(devedor)] -= saldoPagantes[pagantes.indexOf(pagante)];
                    sSugestoes[i] = devedor.getNome() + " paga " +
                            -1*saldoDevedores[devedores.indexOf(devedor)] + " para " + //Cria a string de sugestao
                            pagante.getNome();
                    ++i;
                }
            }

        }
        ArrayList<String> sugestoes = new ArrayList<String>();
        for(int j = 0; j < sSugestoes.length;j++){
            sugestoes.add(sSugestoes[j]);
        }
        return sugestoes;//retorna as strings de sugestão
    }
}
