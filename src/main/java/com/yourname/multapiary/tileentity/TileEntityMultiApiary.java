// ... (в методе updateEntity класса TileEntityMultiApiary, после блока if (logic.canWork()))

        // Auto-mode logic
        if (autoMode) {
            IBee queen = BeeManager.beeRoot.getMember(beeInventory.getQueen());
            if (queen == null) {
                // Find a new princess/queen and a drone from the output inventory to insert
                ItemStack newQueen = null;
                ItemStack newDrone = null;
                int queenSlot = -1;
                int droneSlot = -1;

                for (int j = OUTPUT_SLOTS_START; j < INVENTORY_SIZE; j++) {
                    ItemStack stack = inventory[j];
                    if (stack != null) {
                        if (newQueen == null && BeeManager.beeRoot.isMember(stack, EnumBeeType.PRINCESS) || BeeManager.beeRoot.isMember(stack, EnumBeeType.QUEEN)) {
                            newQueen = stack;
                            queenSlot = j;
                        } else if (newDrone == null && BeeManager.beeRoot.isMember(stack, EnumBeeType.DRONE)) {
                            newDrone = stack;
                            droneSlot = j;
                        }
                    }
                }

                if (newQueen != null && newDrone != null) {
                    beeInventory.setQueen(newQueen);
                    beeInventory.setDrone(newDrone);
                    inventory[queenSlot] = null;
                    inventory[droneSlot] = null;
                    markDirty();
                }
            }
        }

// ... (остальной код метода)
