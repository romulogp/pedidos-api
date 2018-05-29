package com.hustik.pedidosapi.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 *
 * @author Rômulo Göelzer Portolann
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Integer status;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId// garante que o ID do pagamento seja o mesmo do pagamento
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Long id, StatusPagamento status, Pedido pedido) {
        this.id = id;
        this.status = status.getCod();
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPagamento getStatus() {
        return StatusPagamento.toEnum(status);
    }

    public void setStatus(StatusPagamento status) {
        this.status = status.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        return Objects.equals(this.id, other.id);
    }

}
