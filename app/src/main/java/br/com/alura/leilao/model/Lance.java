package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Lance implements Serializable, Comparable<Lance> {

    private final Usuario usuario;
    private final double valor;

    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public int compareTo(@NonNull Lance lance) {
        if (this.getValor() > lance.getValor()) {
            return -1;
        }
        if (this.getValor() < lance.getValor()) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lance lance = (Lance) o;

        if (Double.compare(lance.valor, valor) != 0) return false;
        return usuario != null ? usuario.equals(lance.usuario) : lance.usuario == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = usuario != null ? usuario.hashCode() : 0;
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
