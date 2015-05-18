package demo.site.ext.freemarker.object;

import freemarker.template.*;
import demo.core.persistence.BuyMapper;
import demo.core.persistence.DemandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import java.util.Map;

/**
 * Created by joe on 10/26/14.
 */
@Component
public class StatisticsHashModel implements TemplateHashModelEx {
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private DemandMapper demandMapper;

    public static interface GetResult{
        TemplateModel get();
    }

    protected Map<String, GetResult> mapper;
    public StatisticsHashModel() {
        mapper=new HashMap<String, GetResult>(){{
            put("totalDemandAmount", ()-> new SimpleNumber(demandMapper.getSumPassDemand()));
            put("totalSupplyAmount", ()-> new SimpleNumber(buyMapper.getSumPassSellInfo()));
            put("totalAnthraciteDemandAmount", ()-> new SimpleNumber(demandMapper.getAnthraciteDemand()));
            put("totalAnthraciteSupplyAmount", ()-> new SimpleNumber(buyMapper.getAnthraciteSellInfo()));
        }};
    }

    @Override
    public int size() throws TemplateModelException {
        return mapper.size();
    }

    @Override
    public TemplateCollectionModel keys() throws TemplateModelException {
        return new SimpleCollection(mapper.keySet().iterator());
    }

    @Override
    public TemplateCollectionModel values() throws TemplateModelException {
        throw new RuntimeException("not implemented");
    }

    @Override
    public TemplateModel get(String key) throws TemplateModelException {
        return mapper.get(key).get();
    }

    @Override
    public boolean isEmpty() throws TemplateModelException {
        return false;
    }
}
