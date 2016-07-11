package com.etsy.android.lib.models.taxonomy;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Taxonomy {
    private Map<EtsyId, TaxonomyNode> mIndexById;
    private final List<TaxonomyNode> mTopLevelNodes;

    public Taxonomy(List<TaxonomyNode> list) {
        this.mTopLevelNodes = list;
    }

    public List<TaxonomyNode> getTopLevelNodes() {
        if (!EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1190I)) {
            return this.mTopLevelNodes;
        }
        List<TaxonomyNode> arrayList = new ArrayList();
        for (TaxonomyNode taxonomyNode : this.mTopLevelNodes) {
            if (!taxonomyNode.isSupplies()) {
                arrayList.add(taxonomyNode);
            }
        }
        return arrayList;
    }

    public TaxonomyNode getTaxonomyNodeById(EtsyId etsyId) {
        if (this.mIndexById == null) {
            this.mIndexById = new HashMap();
            for (TaxonomyNode buildIdToNodeIndex : this.mTopLevelNodes) {
                buildIdToNodeIndex(this.mIndexById, buildIdToNodeIndex);
            }
        }
        return (TaxonomyNode) this.mIndexById.get(etsyId);
    }

    private static void buildIdToNodeIndex(Map<EtsyId, TaxonomyNode> map, TaxonomyNode taxonomyNode) {
        map.put(taxonomyNode.getTaxonomyNodeId(), taxonomyNode);
        for (TaxonomyNode buildIdToNodeIndex : taxonomyNode.getChildren()) {
            buildIdToNodeIndex(map, buildIdToNodeIndex);
        }
    }
}
