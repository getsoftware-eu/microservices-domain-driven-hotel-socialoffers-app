package eu.getsoftware.hotelico.streams.topdeal.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ViewTopDealAggregate {
    private int amountViews;
    private int amountOrders;

    @Tolerate
    public ViewTopDealAggregate() {}
}
