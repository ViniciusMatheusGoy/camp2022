/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Vinicius Matheus
 */
public class Jogo {

    private String timeA;
    private String timeB;
    private Integer golA;
    private Integer golB;

    public Jogo() {
    }

    public Jogo(String linha) {

        // 15 de Novembro,7,A. A. Portuguesa,2
        String[] partes = linha.split(",");
        timeA = partes[0];
        timeB = partes[2];
        golA = Integer.parseInt(partes[1]);
        golB = Integer.parseInt(partes[3]);
    }

    public String getTimeA() {
        return timeA;
    }

    public void setTimeA(String timeA) {
        this.timeA = timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public void setTimeB(String timeB) {
        this.timeB = timeB;
    }

    public Integer getGolA() {
        return golA;
    }

    public void setGolA(Integer golA) {
        this.golA = golA;
    }

    public Integer getGolB() {
        return golB;
    }

    public void setGolB(Integer golB) {
        this.golB = golB;
    }

    @Override
    public String toString() {
        return "Jogo{" + "timeA=" + timeA + ", timeB=" + timeB + ", golA=" + golA + ", golB=" + golB + '}';
    }

}
