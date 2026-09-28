class Roupa(
    val descricao: String,
    val pesoKg: Double
)

class ServicoLavagem(
    val tipoLavagem: Int,
    val removedorManchas: String? = null
)

class PedidoLavanderia(
    val cliente: String,
    val roupas: List<Roupa>,
    val servico: ServicoLavagem
)

fun calcularPrecoPorKg(tipoLavagem: Int): Double {
    return when (tipoLavagem) {
        1 -> 15.0 // Seco
        2 -> 12.0 // Delicada
        3 -> 10.0 // Pesada
        else -> 0.0
    }
}
fun obterNomeLavagem(tipoLavagem: Int): String {
    return when (tipoLavagem) {
        1 -> "Seco"
        2 -> "Delicada"
        3 -> "Pesada"
        else -> "Tipo invalido"
    }
}

fun calcularFrete(pesoTotal: Double): Double {
    return if (pesoTotal >= 5.0) {
        0.0
    } else {
        10.0
    }
}

fun calcularRemovedor(removedorManchas: String?): Double {
    return if (removedorManchas != null) {
        15.0
    } else {
        0.0
    }
}

fun calcularPesoTotal(roupas: List<Roupa>): Double {
    var pesoTotal = 0.0

    for (roupa in roupas) {
        pesoTotal += roupa.pesoKg
    }

    return pesoTotal
}

fun calcularTotalPedido(pedido: PedidoLavanderia): Double {
    val pesoTotal = calcularPesoTotal(pedido.roupas)
    val precoPorKg = calcularPrecoPorKg(pedido.servico.tipoLavagem)

    val valorLavagem = pesoTotal * precoPorKg
    val valorRemovedor = calcularRemovedor(pedido.servico.removedorManchas)
    val valorFrete = calcularFrete(pesoTotal)

    return valorLavagem + valorRemovedor + valorFrete
}

fun main() {
    val roupas = listOf(
        Roupa("Camisa", 1.5),
        Roupa("Calca", 2.0),
        Roupa("Toalha", 2.0)
    )

    val servico = ServicoLavagem(
        tipoLavagem = 1,
        removedorManchas = "Removedor especifico"
    )

    val pedido = PedidoLavanderia(
        cliente = "Cliente Teste",
        roupas = roupas,
        servico = servico
    )

    val pesoTotal = calcularPesoTotal(roupas)
    val total = calcularTotalPedido(pedido)
    val nomeLavagem = obterNomeLavagem(servico.tipoLavagem)

    println("=== APP DE LAVANDERIA ===")
    println("Cliente: ${pedido.cliente}")
    println("Tipo de lavagem: $nomeLavagem")
    println("Roupas:")
    for (roupa in roupas) {
        println("- ${roupa.descricao}: ${roupa.pesoKg} kg")
    }
    println("Peso total: $pesoTotal kg")
    if (servico.removedorManchas != null) {
        println("Removedor de manchas: Sim (+ R$ 15,00)")
    } else {
        println("Removedor de manchas: Nao")
    }

    if (pesoTotal >= 5.0) {
        println("Frete: Gratis")
    } else {
        println("Frete: R$ 10,00")
    }
    println("Valor total: R$ %.2f".format(total))
}