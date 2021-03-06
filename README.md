# desafio-loja-virtual

<b>loja-virtual-client:</b> Projeto Maven utilizando Spring Boot + REST (Client) + Spring MVC + Thymeleaf + bootstrap
Este projeto possui a parte web da loja virtual, com um formulario simples simulando a finalização da compra em um carrinho. Existe
também sessão para consultar os pedidos já realizados e uma busca por id da transação. Todas as chamadas ao servidor são realizadas 
via Rest, sendo que as compras são realizadas de maneira assincrona multi-threading.

<b>loja-virtual-server:</b> Projeto Maven utilizando Spring Boot + REST (Server) + JPA + RabbitMQ + DB H2.
Este projeto recebe as requisições de compras originadas da loja-virtual-client via rest. Estas compras são adicionadas a fila
do RabbitMQ que por sua vez é persistida no banco de dados. A fila de compras foi criada com o objetivo de tratar a atualização da
quantidade de produtos no estoque e dando preferência a quem realizou o pedido primeiro.

Obs.:
- Url loja-virtual-client: http://localhost:8888/home
- Url loja-virtual-server: http://localhost:8080/ + Path do serviço rest
- Existe um script (data.sql) de bd que é executado na inicialização e alimenta a base de produto toda vez que a loja-virtual-server é iniciado.
- Para que a home do client seja aberta, o server deverá estar sendo executado.
- Para execução da aplicação cliente: Pode ser feita importando o projeto no eclipse e executando a classe LojaVirtualClientApplication.java ou executando o jar na pasta target via cmd.
- Para execução da aplicação servidor: Pode ser feita importando o projeto no eclipse e executando a classe LojaVirtualServerApplication.java ou executando o jar na pasta target via cmd.

