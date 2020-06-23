
def balance
    system 'curl -X GET "http://localhost:8080/account/HIRO/balance" -H "accept: application/json"'
end

def deposit
    system 'curl -X POST "http://localhost:8080/account/HIRO/deposit" -H "accept: */*" -H "Content-Type: application/json" -d "{\"amount\":1000,\"name\":\"salary\",\"who\":\"my company\"}"'
end

def summary
    system 'curl -X GET "http://localhost:8080/account/HIRO/summary" -H "accept: application/json"'
end

def withdraw
    system 'curl -X POST "http://localhost:8080/account/HIRO/withdraw" -H "accept: */*" -H "Content-Type: application/json" -d "{\"amount\":100,\"name\":\"make payment\",\"who\":\"credit card company\"}"'
end

while true do
    deposit() if rand(2).even?
    summary() if rand(2).even?
    balance() if rand(2).even?
    withdraw() if rand(2).even?
   
    sleep rand(1000) / 1000
end
