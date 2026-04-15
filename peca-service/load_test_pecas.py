import urllib.request
import json
import time
import random

# Configurações
# BASE_URL = "http://localhost:8080/pecas"  # Via API Gateway
BASE_URL = "http://localhost:8082/pecas" # Direto no serviço se necessário

def send_get_request():
    try:
        with urllib.request.urlopen(BASE_URL) as response:
            status = response.getcode()
            print(f"[GET] Status: {status}")
    except Exception as e:
        print(f"[GET] Erro: {e}")

def send_post_request():
    try:
        # Gerando dados aleatórios para evitar duplicidade se o banco tiver restrições
        peca_id = random.randint(100, 999999)
        data = {
            "id": peca_id,
            "nome": f"Peca Teste {peca_id}",
            "descricao": f"Descricao gerada automaticamente para teste de carga {peca_id}"
        }
        
        json_data = json.dumps(data).encode('utf-8')
        
        req = urllib.request.Request(
            BASE_URL, 
            data=json_data, 
            headers={'Content-Type': 'application/json'},
            method='POST'
        )
        
        with urllib.request.urlopen(req) as response:
            status = response.getcode()
            print(f"[POST] Status: {status} - ID: {peca_id}")
            
    except Exception as e:
        print(f"[POST] Erro: {e}")

def run_load_test(iterations=100, delay=0.1):
    print(f"Iniciando teste de carga para {BASE_URL}...")
    print(f"Total de iterações: {iterations}, Delay: {delay}s")
    
    for i in range(iterations):
        # Alterna entre GET e POST
        if i % 2 == 0:
            send_get_request()
        else:
            send_post_request()
        
        if delay > 0:
            time.sleep(delay)
    
    print("Teste finalizado!")

if __name__ == "__main__":
    # Ajuste o número de iterações e o delay conforme necessário
    # Iterations = 500 vai enviar 250 GETs e 250 POSTs
    run_load_test(iterations=100, delay=0.05)
