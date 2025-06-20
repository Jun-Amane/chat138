import requests
import json

# ===== 配置 =====
BASE_URL = "http://localhost:8080"
LOGIN_URL = f"{BASE_URL}/api/auth/login"
CHAT_BASE_URL = f"{BASE_URL}/api/chat"

USERNAME = "admin"
PASSWORD = "admin"

def login():
    print("正在登录...")
    response = requests.post(LOGIN_URL, json={"username": USERNAME, "password": PASSWORD})
    response.raise_for_status()
    token = response.json().get("token")
    if not token:
        raise Exception("登录失败，未返回 token")
    print("登录成功~")
    return {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json"
    }

def test_chat_api(headers):
    # Step 1: 创建新对话
    print("\n创建新对话")
    message = {"message": "你好呀~ 请介绍一下你自己~"}
    response = requests.post(CHAT_BASE_URL, json=message, headers=headers)
    response.raise_for_status()
    data = response.json()
    uuid = data.get("uuid")
    print("创建成功:", json.dumps(data, ensure_ascii=False, indent=2))

    # Step 2: 继续对话
    print("\n继续对话")
    follow_up = {"message": "你都能做些什么？"}
    response = requests.post(f"{CHAT_BASE_URL}/{uuid}", json=follow_up, headers=headers)
    response.raise_for_status()
    print("回复成功:", json.dumps(response.json(), ensure_ascii=False, indent=2))

    # Step 3: 获取对话记录
    print("\n获取完整对话记录")
    response = requests.get(f"{CHAT_BASE_URL}/{uuid}", headers=headers)
    response.raise_for_status()
    print("记录内容:", json.dumps(response.json(), ensure_ascii=False, indent=2))

    # Step 4: 获取对话列表
    print("\n获取对话列表")
    params = {"page": 1, "size": 10}
    response = requests.get(CHAT_BASE_URL, params=params, headers=headers)
    response.raise_for_status()
    print("对话列表:", json.dumps(response.json(), ensure_ascii=False, indent=2))

    # Step 5: 删除对话
    print("\n 删除对话")
    response = requests.delete(f"{CHAT_BASE_URL}/{uuid}", headers=headers)
    response.raise_for_status()
    print("删除结果:", json.dumps(response.json(), ensure_ascii=False, indent=2))

if __name__ == "__main__":
    try:
        headers = login()
        test_chat_api(headers)
    except requests.RequestException as e:
        print(f"请求异常: {e}")
        if hasattr(e, "response") and e.response is not None:
            print("返回内容:", e.response.text)
