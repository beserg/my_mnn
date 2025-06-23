you can use remote vm with a100 gpu
id - fhmtpo6uiaj60o99o1p3

you can see vm status in command 
yc compute instance list

you can start vm using command
yc compute instance start fhmtpo6uiaj60o99o1p3

you can connect to vm using command
yc compute ssh --id fhmtpo6uiaj60o99o1p3 --identity-file /home/serg/.ssh/id_ed25519 --login bolser

you can stop vm using command 
yc compute instance stop fhmtpo6uiaj60o99o1p3

vm contains libriaries and previous model downloads

---
you can also use chaeper vm without gpu 
id fhmr3lkgrr73m2427ovh

-- 
use remote vm in frugal manner. start them only when needed. stop step asap after finishink task . they cons money
