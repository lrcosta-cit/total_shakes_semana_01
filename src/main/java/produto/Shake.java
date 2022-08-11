package produto;

import ingredientes.Adicional;
import ingredientes.Base;
import ingredientes.Fruta;
import ingredientes.Topping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shake {
    private Base base;
    private Fruta fruta;
    private Topping topping;
    private List<Adicional> adicionais;
    private TipoTamanho  tipoTamanho;

    public Shake(Base base, Fruta fruta, Topping topping, List<Adicional> adicionais, TipoTamanho tipoTamanho) {
        this.base = base;
        this.fruta = fruta;
        this.topping = topping;
        this.adicionais = adicionais;
        this.tipoTamanho = tipoTamanho;
    }

    public Shake(Base base, Fruta fruta, Topping topping, TipoTamanho tipoTamanho) {
        this.base = base;
        this.fruta = fruta;
        this.topping = topping;
        this.adicionais = new ArrayList<>();
        this.tipoTamanho = tipoTamanho;
    }

    public Base getBase() {
        return base;
    }

    public Fruta getFruta() {
        return fruta;
    }

    public Topping getTopping() {
        return topping;
    }

    public List<Adicional> getAdicionais() {
        adicionais.sort(new Comparator<Adicional>() {
            @Override
            public int compare(Adicional o1, Adicional o2) {
                return o1.obterTipo().toString().compareToIgnoreCase(o2.obterTipo().toString());
            }
        });

        return adicionais;
    }

    public TipoTamanho getTipoTamanho() {
        return tipoTamanho;
    }

    @Override
    public String toString() {
        return this.base.getTipoBase().toString() + " / " + this.fruta.getTipoFruta().toString() + " / " + this.topping.getTipoTopping().toString() + " / " + this.adicionais + " / " + this.tipoTamanho.toString();
    }
}
