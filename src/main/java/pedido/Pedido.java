package pedido;

import ingredientes.Adicional;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens, Cliente cliente) {
        this.id = id;
        this.itens = itens;
        this.cliente = cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio) {
        double total = 0;
        double parcial = 0;
        System.out.println("CARDÁPIO LKZ: " + cardapio);
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
        if (itemPedidoAdicionado != null) {
            if (this.itens.contains(itemPedidoAdicionado)) {
                if (this.itens.get(this.itens.indexOf(itemPedidoAdicionado)).getShake().equals(itemPedidoAdicionado.getShake())) {
                    this.itens.get(itens.indexOf(itemPedidoAdicionado))
                            .setQuantidade(this.itens.get(this.itens.indexOf(itemPedidoAdicionado)).getQuantidade() + itemPedidoAdicionado.getQuantidade());
                }
            } else {
                this.itens.add(itemPedidoAdicionado);
            }
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        if (itemPedidoRemovido != null) {
            if (!this.itens.isEmpty()) {
                if (this.itens.contains(itemPedidoRemovido) && this.itens.get(this.itens.indexOf(itemPedidoRemovido)).getQuantidade() > 1) {
                    this.itens.get(itens.indexOf(itemPedidoRemovido))
                            .setQuantidade(this.itens.get(this.itens.indexOf(itemPedidoRemovido)).getQuantidade() - 1);
                } else if (this.itens.contains(itemPedidoRemovido) && itemPedidoRemovido.getQuantidade() < this.itens.get(this.itens.indexOf(itemPedidoRemovido)).getQuantidade()) {
                    this.itens.get(itens.indexOf(itemPedidoRemovido))
                            .setQuantidade(this.itens.get(this.itens.indexOf(itemPedidoRemovido)).getQuantidade() - itemPedidoRemovido.getQuantidade());
                } else if (this.itens.contains(itemPedidoRemovido)) {
                    this.itens.remove(itemPedidoRemovido);
                } else {
                    throw new IllegalArgumentException("Item nao existe no pedido.");
                }
            } else {
                throw new IllegalArgumentException("Item nao existe no pedido.");
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
