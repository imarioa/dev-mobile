# DevMobile

#!/bin/bash
acao=$1
param1=$2
param2=$3

case $acao in
        adicionar)
            if [ -e agenda.db ]
            then
                echo "$param1: $param2" >> agenda.db
                echo "Usuário $param1 adicionado."
            else
                echo "$param1: $param2" > agenda.db
                echo "Arquivo criado!!!"
                echo "Usuário $param1 adicionado."
            fi
            ;;
        remover)
            if grep "$param1" agenda.db >> /dev/null
            then
                #param2=$( grep "$param1" agenda.db | cut -d: -f1) 
                grep "$param1" agenda.db | sed -i '1d' agenda.db 2> /dev/null
                echo "Usuário $param2 removido."
                cat agenda.db
            else
                echo "Usuário não existe!!!"
            fi
            ;;
        listar)
            if [ -e agenda.db ]
            then
                cat agenda.db
            else
                echo "Arquivo vazio!!!"
            fi
            ;;
        *)
            echo "Comando inválido!!!"
esac
