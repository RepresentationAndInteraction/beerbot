last_order_id(1). // initial belief
stock(beer, 100).

// plan to achieve the goal "order" for agent Ag
+!order(Product,Qtd)[source(Ag)]
	: stock(Product, InStock) & InStock >= Qtd
	<- ?last_order_id(N);
	   OrderId = N + 1;
	   -+last_order_id(OrderId);
	   -+stock(Product, InStock-Qtd);
	   deliver(Product,Qtd);
	   .send(Ag, tell, delivered(Product,Qtd,OrderId)).

+!order(Product, Qtd)[source(Ag)]
	: true
	<- .concat("Not enough ", Product, " in stock!", M);
	   .send(Ag,tell,msg(M)).
