/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius Matheus
 */
public class Time {

    private String nome;
    private int vitorias = 0;
    private int empates = 0;
    private int derrotas = 0;
    private int golPos = 0;
    private int golNeg = 0;
    private int classificacao = 0;

    List<Jogo> jogos = new ArrayList<Jogo>();

    public Time(String nome, int vitorias, int empates, int derrotas, int golPos, int golNeg) {
        this.nome = nome;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.golPos = golPos;
        this.golNeg = golNeg;
    }

    public Time(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getGolPos() {
        return golPos;
    }

    public void setGolPos(int golPos) {
        this.golPos = golPos;
    }

    public int getGolNeg() {
        return golNeg;
    }

    public void setGolNeg(int golNeg) {
        this.golNeg = golNeg;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    // Métodos adicionais
    public void addVitoria() {
        this.vitorias += 1;
    }

    public void addDerrota() {
        this.derrotas += 1;
    }

    public void addEmpate() {
        this.empates += 1;
    }

    public void addGolPos(int gols) {
        this.golPos += gols;
    }

    public void addGolNeg(int gols) {
        this.golNeg += gols;
    }

    public int getSaldoGols() {
        return this.golPos - this.golNeg;
    }

    public int getPontos() {
        return (this.vitorias * 3) + this.empates;
    }

    @Override
    public String toString() {
        return nome;
    }

}
