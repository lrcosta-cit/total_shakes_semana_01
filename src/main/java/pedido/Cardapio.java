package pedido;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente, Double> precos;

    public Cardapio() {
        this.precos = new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos() {
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente, Double preco) {
        if (ingrediente != null && (preco != null && preco > 0.0)) {
            this.precos.put(ingrediente, preco);
        } else {
            throw new IllegalArgumentException("Preco invalido.");
        }
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente, Double preco) {
        if (this.precos.containsKey(ingrediente)) {
            if(preco != null && preco > 0.0){
                this.precos.replace(ingrediente, this.precos.get(ingrediente), preco);
            } else {
                throw new IllegalArgumentException("Preco invalido.");
            }
        } else {
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
        return false;
    }

    public boolean removerIngrediente(Ingrediente ingrediente) {
        if(ingrediente != null){
            if(this.precos.containsKey(ingrediente)){
                this.precos.remove(ingrediente);
            } else {
                throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
            }
        }
        return false;
    }

    public Double buscarPreco(Ingrediente ingrediente) {
        Double preco;
        if (this.precos.containsKey(ingrediente)) {
            preco = this.precos.get(ingrediente);
            return preco;
        } else {
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }

    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
