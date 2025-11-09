// ... (в классе BeeHousingModifier)

    @Override
    public IAllele getFlowerAllele(IBeeGenome genome) {
        if (hasModifier(ItemModifierMultiFlower.class)) {
            return AlleleManager.alleleFactory.createString("flowers", "all", "forestry.flowers.all");
        }
        return genome.getFlowerAllele();
    }

// ... (остальной код класса)
