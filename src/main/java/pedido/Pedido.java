package pedido;

import ingredientes.Adicional;

import java.util.ArrayList;
import java.util.List;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio) {
        double total = 0;
        double parcial = 0;
        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getShake() != null) {
                if (itemPedido.getShake().getBase() != null) {
                    if (cardapio.getPrecos().containsKey(itemPedido.getShake().getBase())) {
                        parcial += cardapio.getPrecos().get(itemPedido.getShake().getBase());
                    }
                }
                if (itemPedido.getShake().getTipoTamanho() != null) {
                    parcial *= itemPedido.getShake().getTipoTamanho().getMultiplicador();
                }

                if (!itemPedido.getShake().getAdicionais().isEmpty()) {
                    List<Adicional> adicionais = itemPedido.getShake().getAdicionais();
                    for (Adicional adicional : adicionais) {
                        if (cardapio.getPrecos().containsKey(adicional)) {
                            parcial += cardapio.getPrecos().get(adicional);
                        }
                    }
                }

                if (itemPedido.getQuantidade() > 1) {
                    parcial *= itemPedido.getQuantidade();
                }
            }
            total += parcial;
            parcial = 0;
        }

        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado) {
        boolean itemExists = false;
        if (itemPedidoAdicionado != null) {
            for (ItemPedido itemPedido : this.itens) {
                if (itemPedido.getShake().equals(itemPedidoAdicionado.getShake())) {
                    itemPedido.setQuantidade(itemPedido.getQuantidade() + itemPedidoAdicionado.getQuantidade());
                    itemExists = true;
                }
            }
            if (!itemExists) {
                this.itens.add(itemPedidoAdicionado);
            }
        }
    }


    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        if (itemPedidoRemovido != null) {
            if (!this.itens.isEmpty()) {
                for (ItemPedido itemPedido : this.itens) {
                    if (itemPedido.getShake().toString().equals(itemPedidoRemovido.getShake().toString())) {
                        if (itemPedido.getQuantidade() > 1) {
                            itemPedido.setQuantidade(itemPedido.getQuantidade() - 1);
                        } else {
                            this.itens.remove(itemPedido);
                        }
                        break;
                    } else {
                        throw new IllegalArgumentException("Item nao existe no pedido.");
                    }
                }
            }

        }
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
