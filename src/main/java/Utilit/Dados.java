/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilit;

import Model.Jogo;
import Model.Time;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Vinicius Matheus
 */
public class Dados {

    private String nomeArq;

    private BufferedReader br = null;

    private Jogo jogoLinha;

    List<Time> lstTimes = new ArrayList<>();

    public Dados(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public List<Time> lerJogo() {
        try {
            String linha;
            br = new BufferedReader(new FileReader(nomeArq));
            while ((linha = br.readLine()) != null) {
                jogoLinha = new Jogo(linha);
                analisaJogo(jogoLinha);
            }
            // ordena1();  // Somente Pontos
            ordena2();  // Pontos, Vitorias, Saldo de Gols
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTimes;
    }

    private Time achaTime(String nomeBusca) {
        for (Time t : lstTimes) {
            if (t.getNome().equals(nomeBusca)) {
                return t;
            }
        }
        Time novo = new Time(nomeBusca);
        lstTimes.add(novo);
        return novo;
    }

    private void analisaJogo(Jogo jg) {
        Time timeA, timeB;

        timeA = achaTime(jg.getTimeA());
        timeB = achaTime(jg.getTimeB());

        timeA.getJogos().add(jg);
        timeB.getJogos().add(jg);

        if (jg.getGolA() > jg.getGolB()) { // Time A ganhou
            timeA.addVitoria();
            timeB.addDerrota();
        } else if (jg.getGolA() < jg.getGolB()) { // Time B ganhou
            timeB.addVitoria();
            timeA.addDerrota();
        } else {
            timeA.addEmpate();
            timeB.addEmpate();
        }
        timeA.addGolPos(jg.getGolA());
        timeB.addGolPos(jg.getGolB());
        timeA.addGolNeg(jg.getGolB());
        timeB.addGolNeg(jg.getGolA());
    }

    public void ordena1() {
        Time timeAux;
        int i, j;
        i = 1;
        while (i <= lstTimes.size() - 1) {
            j = i;
            while ((j > 0)
                    && ((lstTimes.get(j - 1).getPontos()
                    < lstTimes.get(j).getPontos()))) // Pontos 
            {
                timeAux = lstTimes.get(j - 1);
                lstTimes.set(j - 1, lstTimes.get(j));
                lstTimes.set(j, timeAux);
                j--;
            }
            i++;
        }
        i = 1;
        for (Time t : lstTimes) {
            t.setClassificacao(i);
            i++;
        }
    }

    private void ordena2() {
        Time timeAux;
        int i, j;
        i = 1;
        while (i <= lstTimes.size() - 1) {
            j = i;
            while ((j > 0)
                    && ((lstTimes.get(j - 1).getPontos() < lstTimes.get(j).getPontos()) // Pontos 
                    || // ou
                    ((lstTimes.get(j - 1).getPontos() == lstTimes.get(j).getPontos())
                    && (lstTimes.get(j - 1).getVitorias() < lstTimes.get(j).getVitorias())) // Vitorias 
                    || // ou
                    ((lstTimes.get(j - 1).getPontos() == lstTimes.get(j).getPontos())
                    && (lstTimes.get(j - 1).getVitorias() == lstTimes.get(j).getVitorias())
                    && (lstTimes.get(j - 1).getSaldoGols() < lstTimes.get(j).getSaldoGols())) // Saldo de Gols 
                    )) {
                timeAux = lstTimes.get(j - 1);
                lstTimes.set(j - 1, lstTimes.get(j));
                lstTimes.set(j, timeAux);
                j--;
            }
            i++;
        }
        i = 1;
        for (Time t : lstTimes) {
            t.setClassificacao(i);
            i++;
        }
    }

    public void gravarJogosJSON(Time time) {
        try {
            String nomeArq = "C:\\Dados\\" + time.getNome() + ".json";
            File file = new File(nomeArq);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            JSONObject eJSON = new JSONObject();
            for (Jogo j : time.getJogos()) {
                eJSON.put("timeA", j.getTimeA());
                eJSON.put("golA", j.getGolA());
                eJSON.put("timeB", j.getTimeB());
                eJSON.put("golB", j.getGolB());
                bw.write(eJSON.toString() + "\n");
            }
            bw.close();
        } catch (Exception e) {
        }
    }
}